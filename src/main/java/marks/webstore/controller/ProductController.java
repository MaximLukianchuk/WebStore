package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.domain.Store;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import marks.webstore.service.ProductService;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/products")
    public String products(Map<String, Object> model) {
        model.put("producttypes", productService.findAllProductsReverse());

        return "products";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/productsList")
    public String productsList(Map<String, Object> model) {
        model.put("producttypes", productService.findAllProductsReverse());

        return "redactorProductList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/addProduct")
    public String addProduct(
            @RequestParam String name,
            @RequestParam Float price,
            @RequestParam Long amount,
            @RequestParam String description,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file,
            @RequestParam String storeName,
            Integer discount,
            Model models
    ) throws IOException {
        model.put("stores", storeService.findAllStoresReverse());

        if (productService.findAllProducts().stream().noneMatch(productType -> productType.getName().equals(name))) {

            ProductType productType = new ProductType(name, price, description, discount);

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                productType.setFilename(resultFilename);
            }


            Store store = storeService.findStoreByName(storeName);

            if (store == null) {
                models.addAttribute("addProductError", "Store " + storeName + " does not exist!");
                return "products";
            }

            ProductTypeStore productTypeStore = new ProductTypeStore(productType, store, amount);

            productService.saveProduct(productType);
            productStoreService.saveProductTypeStore(productTypeStore);
        }

        Iterable<ProductType> producttypes = productService.findAllProductsReverse();;

        model.put("producttypes", producttypes);
        return "redactorStoreList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/addProduct")
    public String addProducts (Map<String, Object> model) {
        model.put("stores", storeService.findAllStoresReverse());

        return "redactorProductAdd";
    }
}