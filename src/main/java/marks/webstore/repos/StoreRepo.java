package marks.webstore.repos;

import marks.webstore.domain.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepo extends CrudRepository<Store, Long> {
    List<Store> findAll();
    Store findByName(String name);
    Store findStoreById(Long id);
}