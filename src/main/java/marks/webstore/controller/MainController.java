package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<ProductType> producttypes = productTypeRepo.findAll();

        model.put("producttypes", producttypes);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, Map<String, Object> model) {
        if (productTypeRepo.findAll().stream().noneMatch(productType -> productType.getName().equals(name))) {

            ProductType productType = new ProductType(name);

            productTypeRepo.save(productType);
        }

            Iterable<ProductType> producttypes = productTypeRepo.findAll();

            model.put("producttypes", producttypes);
        return "main";
    }
}
