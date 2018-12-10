package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.Store;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.StoreRepo;
import marks.webstore.service.ProductService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        List<ProductType> productTypesWithDiscount = productService.findAllProductsReverse().stream()
                .filter(productType -> productType.getDiscount() != null)
                .collect(Collectors.toList());

        model.put("producttypeswithdisk", productTypesWithDiscount);

        model.put("stores", storeService.findAllStoresReverse());
        return "greeting";
    }
}
