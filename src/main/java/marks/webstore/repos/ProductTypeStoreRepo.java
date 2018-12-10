package marks.webstore.repos;

import marks.webstore.domain.ProductType;
import marks.webstore.domain.ProductTypeStore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductTypeStoreRepo extends CrudRepository<ProductTypeStore, Long> {
    List<ProductTypeStore> findAllByStoreId(Integer storeId);
    List<ProductTypeStore> findAll();
    ProductTypeStore findStoreByProduct(ProductType productType);
}
