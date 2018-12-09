package marks.webstore.controller;

import marks.webstore.domain.ProductTypeStore;
import marks.webstore.domain.Store;
import marks.webstore.repos.ProductTypeRepo;
import marks.webstore.repos.ProductTypeStoreRepo;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class StoreController {
    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private ProductTypeStoreRepo productTypeStoreRepo;

    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/stores")
    public String stores(Map<String, Object> model) {
        List<Store> storesbd = storeRepo.findAll();
        Collections.reverse(storesbd);

        model.put("stores", storesbd);

        return "stores";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/storesList")
    public String storesList(Map<String, Object> model) {
        List<Store> storesbd = storeRepo.findAll();
        Collections.reverse(storesbd);

        model.put("stores", storesbd);

        return "redactorStoreList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/addStore")
    public String storesAdd(Map<String, Object> model) {
        List<Store> storesbd = storeRepo.findAll();
        Collections.reverse(storesbd);

        model.put("stores", storesbd);

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
        if (storeRepo.findAll().stream().noneMatch(store -> store.getName().equals(name))) {

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

            storeRepo.save(newStore);
        }

        List<Store> storedb = storeRepo.findAll();
        Collections.reverse(storedb);
        Iterable<Store> stores = storedb;

        model.put("stores", stores);
        return "stores";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/stores/{store}")
    public String storeProductsList(@PathVariable("store") String storeID, Map<String, Object> models, Model model) {
        Long store_id = Long.parseLong(storeID);

        Store store = storeRepo.findStoreById(store_id);

        List<ProductTypeStore> productTypeStores = productTypeStoreRepo.findAllByStoreId(store.getId());
        Collections.reverse(productTypeStores);

        model.addAttribute("storeName", store.getName());
        models.put("productTypeStores", productTypeStores);

        return "storeProducts";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/stores/{store}/edit")
    public String editStore(@PathVariable Store store, Model model) {

        model.addAttribute("store", store);
        return "redactorStoreEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/stores/{store}/edit")
    public String updateStore(
            @PathVariable Store store,
            @RequestParam String name,
            @RequestParam String address
    ) {
        store.setName(name);
        store.setAddress(address);

        storeRepo.save(store);

        return "redirect:/stores/{store}";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("stores/{store}/delete")
    public String deleteStore(
            @PathVariable Store store) {
        storeRepo.delete(store);
        return "redirect:/stores";
    }
}
