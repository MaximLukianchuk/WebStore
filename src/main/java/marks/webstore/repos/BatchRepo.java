package marks.webstore.repos;

import marks.webstore.domain.Batch;
import org.springframework.data.repository.CrudRepository;

public interface BatchRepo extends CrudRepository<Batch, Long> {
}
