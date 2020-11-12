package fishing.data;


import fishing.Fish;
import fishing.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FishRepository extends CrudRepository<Fish, Long> {
    List<Fish> findAllByUser(User user, Pageable pageable);
}
