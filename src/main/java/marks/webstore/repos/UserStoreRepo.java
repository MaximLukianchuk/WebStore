package marks.webstore.repos;

import marks.webstore.domain.Store;
import marks.webstore.domain.User;
import marks.webstore.domain.UserStore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserStoreRepo extends CrudRepository<UserStore, Long> {
    List<UserStore> findAll();
    List<UserStore> findAllByUser(User user);
    UserStore findByStore(Store store);
}
