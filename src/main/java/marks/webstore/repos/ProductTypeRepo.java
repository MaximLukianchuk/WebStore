package marks.webstore.repos;

import marks.webstore.domain.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductTypeRepo extends CrudRepository<ProductType, Long> {
    List<ProductType> findAll();
}
