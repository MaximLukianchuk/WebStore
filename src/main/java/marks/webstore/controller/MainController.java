package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.Store;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.StoreRepo;
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
    private ProductTypeRepo productTypeRepo;

    @Autowired
    private StoreRepo storeRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        List<ProductType> productTypes = productTypeRepo.findAll();
        Collections.reverse(productTypes);
        List<ProductType> productTypesWithDiscount = productTypes.stream()
                .filter(productType -> productType.getDiscount() != null)
                .collect(Collectors.toList());

        model.put("producttypeswithdisk", productTypesWithDiscount);

        List<Store> storesbd = storeRepo.findAll();
        Collections.reverse(storesbd);

        model.put("stores", storesbd);
        return "greeting";
    }
}
