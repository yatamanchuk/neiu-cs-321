package fishing.data;


import fishing.Fish;
import fishing.User;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface FishRepository extends CrudRepository<Fish, Long> {
    /*List<Fish> findAllBy(User user, Pageable pageable);*/

    List<Fish> findAllByUser(User user);
}
