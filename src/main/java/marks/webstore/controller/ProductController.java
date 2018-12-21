package marks.webstore.controller;

import marks.webstore.domain.*;
import marks.webstore.repos.UserStoreRepo;
import marks.webstore.service.ProductService;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private UserStoreRepo userStoreRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/products")
    public String products(Map<String, Object> model) {
        model.put("producttypes", productService.findAllPublishedProductsReverse());

        return "products";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/productsList")
    public String productsList(
            Map<String, Object> model,
            @AuthenticationPrincipal User user) {
        model.put("producttypes", user.isAdmin() ? productService.findAllProducts().stream()
                .sorted(Comparator.comparing(ProductType::getPublished))
                .collect(Collectors.toList()) :
                productService.findAllProducts().stream()
                        .filter(productType -> productStoreService.isRedactorHasRoot(user, productType))
                        .sorted(Comparator.comparing(ProductType::getPublished))
                        .collect(Collectors.toList()));

        return "redactorProductList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/addProduct")
    public String addProduct(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Long amount,
            @RequestParam String description,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file,
            @RequestParam String storeName,
            Integer discount,
            Model models
    ) throws IOException {
        if (!productStoreService.isRedactorHasRoot(user, storeService.findStoreByName(storeName))) {
            return "resources/public/error/403";
        }

        price = (double) Math.round(price * 100d) / 100d;
        model.put("stores", storeService.findAllStoresReverse());

        if (productService.findAllProducts().stream().noneMatch(productType -> productType.getName().equals(name))) {

            ProductType productType = new ProductType(name, price, description, discount, user.isAdmin(), false);

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

        model.put("producttypes", user.isAdmin() ? productService.findAllProductsReverse().stream()
                .sorted(Comparator.comparing(ProductType::getPublished))
                .collect(Collectors.toList()) :
                productService.findAllProductsReverse().stream()
                        .filter(productType -> productStoreService.isRedactorHasRoot(user, productType))
                        .sorted(Comparator.comparing(ProductType::getPublished))
                        .collect(Collectors.toList()));

        return "redactorProductList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/addProduct")
    public String addProducts(
            @AuthenticationPrincipal User user,
            Map<String, Object> model) {
        model.put("stores", user.isAdmin() ? storeService.findAllStoresReverse() :
                userStoreRepo.findAllByUser(user).stream()
                        .map(UserStore::getStore)
                        .collect(Collectors.toList()));

        return "redactorProductAdd";
    }
}