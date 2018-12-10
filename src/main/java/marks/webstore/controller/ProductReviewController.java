package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.service.ProductService;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductReviewController {

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductService productService;

    @GetMapping("{product}")
    public String productReviewForm(@PathVariable ProductType product, Model model) {
        ProductTypeStore productTypeStore = productStoreService.findStoreByProduct(product);

        model.addAttribute("store", productTypeStore);
        model.addAttribute("product", product);
        return "productReview";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/edit")
    public String editProduct(@PathVariable ProductType product, Model model) {
        Long amountOfProducts = productStoreService.findAllByProductId(product.getId()).get(0).getAmount();
        model.addAttribute("product", product);
        model.addAttribute("stores", storeService.findAllStores());
        model.addAttribute("amountofproducts", amountOfProducts);
        return "redactorProductEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("{product}/edit")
    public String updateProduct(
            @PathVariable ProductType product,
            @RequestParam String name,
            @RequestParam String storeName,
            @RequestParam Float price,
            @RequestParam Long amount,
            @RequestParam String description
            ) {
        productService.updateProduct(product, name, storeName, price, amount, description);

        return "redirect:/products/{product}";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/delete")
    public String deleteProduct(
            @PathVariable ProductType product) {
        productService.deleteProduct(product);
        return "redirect:/products";
    }
}
