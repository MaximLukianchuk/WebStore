package marks.webstore.repos;

import marks.webstore.domain.Batch;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BatchRepo extends CrudRepository<Batch, Long> {
}
