package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.service.ProductStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductReviewController {

    @Autowired
    private ProductStoreService productStoreService;

    @GetMapping("{product}")
    public String productReviewForm(@PathVariable ProductType product, Model model) {
        ProductTypeStore productTypeStore = productStoreService.findStoreByProduct(product);

        model.addAttribute("store", productTypeStore);
        model.addAttribute("product", product);
        return "productReview";
    }
}
