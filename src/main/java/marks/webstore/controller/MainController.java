package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.repos.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
public class MainController {
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        List<ProductType> productTypes = productTypeRepo.findAll();
        Collections.reverse(productTypes);
        Iterable<ProductType> producttypes = productTypes;

        model.put("producttypes", producttypes);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam String name,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (productTypeRepo.findAll().stream().noneMatch(productType -> productType.getName().equals(name))) {

            ProductType productType = new ProductType(name);

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

            productTypeRepo.save(productType);
        }

        List<ProductType> productTypes = productTypeRepo.findAll();
        Collections.reverse(productTypes);
        Iterable<ProductType> producttypes = productTypes;

        model.put("producttypes", producttypes);
        return "main";
    }
}
