package marks.webstore.service;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.domain.Store;
import marks.webstore.domain.User;
import marks.webstore.repos.ProductTypeStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductStoreService {
    @Autowired
    private ProductTypeStoreRepo productTypeStoreRepo;

    public void saveProductTypeStore(ProductTypeStore productTypeStore) {
        productTypeStoreRepo.save(productTypeStore);
    }

    public ProductTypeStore findStoreByProduct(ProductType productType) {
        return productTypeStoreRepo.findStoreByProduct(productType);
    }

    public List<ProductTypeStore> findAllByStoreIdReverse(Long storeId) {
        List<ProductTypeStore> productTypeStores = productTypeStoreRepo.findAllByStoreId(storeId);
        Collections.reverse(productTypeStores);

        return productTypeStores;
    }

    public List<ProductTypeStore> findAllProductStores() {
        List<ProductTypeStore> productTypeStores = productTypeStoreRepo.findAll();
        Collections.reverse(productTypeStores);

        return productTypeStores;
    }

    public Long getAmountOfProducts(ProductType product) {
        return productTypeStoreRepo.findAllByProductId(product.getId()).stream().findAny().get().getAmount();
    }

    public List<ProductTypeStore> findAllByProductId(Long productId) {
        List<ProductTypeStore> productTypeStores = productTypeStoreRepo.findAllByProductId(productId);
        Collections.reverse(productTypeStores);

        return productTypeStores;
    }

    public boolean isRedactorHasRoot(User user, ProductType product) {
        if (user.isRedactor()) {
            Long contextProductStoreId = productTypeStoreRepo.findStoreByProduct(product).getStore().getId();
            Set<Long> contextUserStoresId = user.getUserStores().stream()
                    .map(userStore -> userStore.getStore().getId())
                    .collect(Collectors.toSet());
            return contextUserStoresId.contains(contextProductStoreId);
        }
        return user.isAdmin();
    }

    public boolean isRedactorHasRoot(User user, Store store) {
        if (user.isRedactor()) {
            Long contextStoreId = store.getId();
            Set<Long> contextUserStoresId = user.getUserStores().stream()
                    .map(userStore -> userStore.getStore().getId())
                    .collect(Collectors.toSet());
            return contextUserStoresId.contains(contextStoreId);
        }
        return user.isAdmin();
    }
}
