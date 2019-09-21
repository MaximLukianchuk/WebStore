package marks.webstore.service;

import marks.webstore.domain.Store;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    @Autowired
    private StoreRepo storeRepo;

    public List<Store> findAllStoresReverse() {
        List<Store> storesBd = storeRepo.findAll();
        Collections.reverse(storesBd);

        return storesBd;
    }

    public List<Store> findAllStores() {
        return storeRepo.findAll();
    }

    public List<Store> findAllPublishedStores() {
        return findAllStores().stream()
                .filter(Store::getPublished)
                .collect(Collectors.toList());
    }

    public List<Store> findAllPublishedStoresReverse() {
        List<Store> storesBd = findAllStores().stream()
                .filter(Store::getPublished)
                .collect(Collectors.toList());
        Collections.reverse(storesBd);

        return storesBd;
    }

    public Store findStoreByName(String storeName) {
        return storeRepo.findByName(storeName);
    }

    public void saveStore(Store store) {
        storeRepo.save(store);
    }


    public Store findStoreById(Long id) {
        return storeRepo.findStoreById(id);
    }

    public void updateStore(Store store, String name, String description, Boolean isPublished) {
        store.setName(name);
        store.setDescription(description);
        store.setPublished(isPublished);

        storeRepo.save(store);
    }

    public void deleteStore(Store store) {
        storeRepo.delete(store);
    }
}
