package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductReviewController {

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private ProductTypeStoreRepo productTypeStoreRepo;

    @Autowired
    private ProductTypeRepo productTypeRepo;

    @GetMapping("{product}")
    public String productReviewForm(@PathVariable ProductType product, Model model) {
        model.addAttribute("product", product);
        return "productReview";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/edit")
    public String editProduct(@PathVariable ProductType product, Model model) {
        Long amountOfProducts = productTypeStoreRepo.findAllByProductId(product.getId()).get(0).getAmount();
        model.addAttribute("product", product);
        model.addAttribute("stores", storeRepo.findAll());
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
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get().setStore(storeRepo.findByName(storeName));
        productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get().setAmount(amount);
        productTypeRepo.save(product);
        productTypeStoreRepo.save(productTypeStoreRepo.findAll().stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getId().equals(product.getId()))
                .findAny().get());

        return "redirect:/products/{product}";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("{product}/delete")
    public String deleteProduct(
            @PathVariable ProductType product) {
        productTypeRepo.delete(product);
        return "redirect:/products";
    }
}
