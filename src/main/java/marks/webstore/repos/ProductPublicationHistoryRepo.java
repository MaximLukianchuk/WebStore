package marks.webstore.repos;

import marks.webstore.domain.ProductPublicationHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPublicationHistoryRepo extends CrudRepository<ProductPublicationHistory, Long> {
     List<ProductPublicationHistory> findAll();
}
