package marks.webstore.controller;

import marks.webstore.domain.ProductTypeStore;
import marks.webstore.domain.Store;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/stores")
    public String stores(Map<String, Object> model) {
        model.put("stores", storeService.findAllStoresReverse());

        return "stores";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/storesList")
    public String storesList(Map<String, Object> model) {
        model.put("stores", storeService.findAllStoresReverse());

        return "redactorStoreList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/addStore")
    public String storesAdd(Map<String, Object> model) {
        model.put("stores", storeService.findAllStoresReverse());

        return "redactorStoreAdd";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/addStore")
    public String addStore(
            @RequestParam String name,
            @RequestParam String address,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (storeService.findAllStores().stream().noneMatch(store -> store.getName().equals(name))) {

            Store newStore = new Store(name, address);

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                newStore.setFilename(resultFilename);
            }

            storeService.saveStore(newStore);
        }

        Iterable<Store> stores = storeService.findAllStoresReverse();

        model.put("stores", stores);
        return "stores";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/stores/{store}")
    public String storeProductsList (@PathVariable("store") String storeID, Map<String, Object> models, Model model) {
        Integer store_id = Integer.parseInt(storeID);

        Store store = storeService.findStoreById(store_id);

        model.addAttribute("storeName", store.getName());
        models.put("productTypeStores", productStoreService.findAllByStoreIdReverse(store.getId()));

        return "storeProducts";
    }
}
