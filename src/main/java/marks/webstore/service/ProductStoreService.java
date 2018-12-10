package marks.webstore.service;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import marks.webstore.repos.ProductTypeStoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;

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

    public List<ProductTypeStore> findAllByStoreIdReverse(Integer storeId) {
        List<ProductTypeStore> productTypeStores = productTypeStoreRepo.findAllByStoreId(storeId);
        Collections.reverse(productTypeStores);

        return productTypeStores;
    }
}
