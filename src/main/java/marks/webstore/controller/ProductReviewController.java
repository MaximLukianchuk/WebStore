package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductReviewController {

    @GetMapping("{product}")
    public String productReviewForm(@PathVariable ProductType product, Model model) {
        model.addAttribute("product", product);
        return "productReview";
    }
}
