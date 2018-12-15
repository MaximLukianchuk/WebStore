package marks.webstore.controller;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.Store;
import marks.webstore.domain.User;
import marks.webstore.domain.UserStore;
import marks.webstore.repos.StoreRepo;
import marks.webstore.repos.UserStoreRepo;
import marks.webstore.service.ProductStoreService;
import marks.webstore.service.StoreService;
import marks.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProductStoreService productStoreService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private UserStoreRepo userStoreRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/stores")
    public String stores(Map<String, Object> model) {
        model.put("stores", storeService.findAllPublishedStores());

        return "stores";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/storesList")
    public String storesList(
            Map<String, Object> model,
            @AuthenticationPrincipal User user) {
        model.put("stores", user.isAdmin() ? storeService.findAllStores().stream()
                .sorted(Comparator.comparing(Store::getPublished))
                .collect(Collectors.toList()) :
                storeService.findAllStores().stream()
                        .filter(store -> productStoreService.isRedactorHasRoot(user, store))
                        .sorted(Comparator.comparing(Store::getPublished))
                        .collect(Collectors.toList()));

        return "redactorStoreList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/addStore")
    public String storesAdd(Map<String, Object> model,
                            @AuthenticationPrincipal User user) {
        if (user.isRedactor() && !user.getAllowedToCreateStores()) {
            return "resources/public/error/403";
        }
        model.put("stores", storeService.findAllStoresReverse());

        return "redactorStoreAdd";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/addStore")
    public String addStore(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (user.isRedactor() && !user.getAllowedToCreateStores()) {
            return "resources/public/error/403";
        }
        if (storeService.findAllStores().stream().noneMatch(store -> store.getName().equals(name))) {

            Store newStore = new Store(name, description, user.isAdmin());

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
            if (user.isRedactor() && user.getAllowedToCreateStores()) {
                UserStore newUserStore = new UserStore(user, newStore);
                userStoreRepo.save(newUserStore);
                user.getUserStores().add(newUserStore);
            }
        }

        model.put("stores", user.isAdmin() ? storeService.findAllStoresReverse().stream()
                .sorted(Comparator.comparing(Store::getPublished))
                .collect(Collectors.toList()) :
                storeService.findAllStoresReverse().stream()
                        .filter(store -> productStoreService.isRedactorHasRoot(user, store))
                        .sorted(Comparator.comparing(Store::getPublished))
                        .collect(Collectors.toList()));
        return "redirect:/storesList";
    }

    @GetMapping("/stores/{store}")
    public String storeProductsList(@PathVariable("store") String storeID, Map<String, Object> models, Model model) {
        if (storeService.findStoreById(Long.parseLong(storeID)) == null ||
                !storeService.findStoreById(Long.parseLong(storeID)).getPublished()) {
            return "resources/public/error/404";
        }
        Long store_id = Long.parseLong(storeID);

        Store store = storeService.findStoreById(store_id);

        model.addAttribute("storeName", store.getName());
        models.put("productTypeStores", productStoreService.findAllByStoreIdReverse(store.getId()).stream()
                .filter(productTypeStore -> productTypeStore.getProduct().getPublished())
                .collect(Collectors.toSet()));

        return "storeProducts";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("/stores/{store}/edit")
    public String editStore(
            @AuthenticationPrincipal User user,
            @PathVariable Store store,
            Model model) {
        if (storeService.findStoreByName(store.getName()) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, store)) {
            return "resources/public/error/403";
        }
        model.addAttribute("store", store);
        return "redactorStoreEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @PostMapping("/stores/{store}/edit")
    public String updateStore(
            @AuthenticationPrincipal User user,
            @PathVariable Store store,
            @RequestParam String name,
            @RequestParam String description
    ) {
        if (storeService.findStoreByName(store.getName()) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, store)) {
            return "resources/public/error/403";
        }
        storeService.updateStore(store, name, description, user.isAdmin());
        return "redirect:/storesList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'REDACTOR')")
    @GetMapping("stores/{store}/delete")
    public String deleteStore(
            @AuthenticationPrincipal User user,
            @PathVariable Store store,
            Map<String, Object> model) {
        if (storeService.findStoreByName(store.getName()) == null) {
            return "resources/public/error/404";
        }
        if (!productStoreService.isRedactorHasRoot(user, store)) {
            return "resources/public/error/403";
        }
        storeService.deleteStore(store);
        model.put("stores", storeService.findAllStoresReverse());
        return "redirect:/storesList";
    }
}
