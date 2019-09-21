package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.domain.User;
import marks.webstore.repos.UserStoreRepo;
import marks.webstore.service.ProductService;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductReviewController {

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserStoreRepo userStoreRepo;

    @GetMapping("{product}")
    public String productReviewForm(@PathVariable ProductType product, Model model) {
        if (productStoreService.findStoreByProduct(product) == null ||
        !product.getPublished()) {
            return "resources/public/error/404";
        }
        ProductTypeStore productTypeStore = productStoreService.findStoreByProduct(product);

        model.addAttribute("store", productTypeStore);
        model.addAttribute("product", product);
        return "productReview";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/edit")
    public String editProduct(@PathVariable ProductType product,
                              Model model,
                              @AuthenticationPrincipal User user) {
        if (productStoreService.findStoreByProduct(product) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, product)) {
            return "resources/public/error/403";
        }
        Long amountOfProducts = productStoreService.getAmountOfProducts(product);
        model.addAttribute("product", product);
        model.addAttribute("stores", storeService.findAllStores());
        model.addAttribute("curStore", productStoreService.findStoreByProduct(product).getStore().getName());
        model.addAttribute("amountofproducts", amountOfProducts);
        return "redactorProductEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("{product}/edit")
    public String updateProduct(
            @PathVariable ProductType product,
            @RequestParam String name,
            String storeName,
            @RequestParam Double price,
            @RequestParam Long amount,
            @RequestParam String description,
            @AuthenticationPrincipal User user
    ) {
        if (productStoreService.findStoreByProduct(product) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, product)) {
            return "resources/public/error/403";
        }
        productService.updateProduct(product, name, storeName, price, amount, description, user.isAdmin(), false);

        return "redirect:/productsList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/delete")
    public String deleteProduct(
            @AuthenticationPrincipal User user,
            @PathVariable ProductType product,
            Map<String, Object> model) {
        if (productStoreService.findStoreByProduct(product) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, product)) {
            return "resources/public/error/403";
        }
        productService.deleteProduct(product);
        model.put("producttypes", user.isAdmin() ?  productService.findAllProductsReverse() :
                productService.findAllProductsReverse().stream()
                        .filter(productType -> productStoreService.isRedactorHasRoot(user, productType))
                        .collect(Collectors.toList()));

        return "redactorProductList";
    }
}
