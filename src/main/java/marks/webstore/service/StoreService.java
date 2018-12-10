package marks.webstore.service;

import marks.webstore.domain.Store;
import marks.webstore.repos.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public Store findStoreByName(String storeName) {
        return storeRepo.findByName(storeName);
    }

    public void saveStore(Store store) {
        storeRepo.save(store);
    }

    public Store findStoreById(Integer id) {
        return storeRepo.findById(id);
    }
}
