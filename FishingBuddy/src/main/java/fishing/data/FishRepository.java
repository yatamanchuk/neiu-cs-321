package fishing.data;


import fishing.Fish;
import org.springframework.data.repository.CrudRepository;


public interface FishRepository extends CrudRepository<Fish, Long> {
}
