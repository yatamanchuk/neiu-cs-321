package fishing.data;

import fishing.Bait;
import fishing.Fish;

import java.util.List;

public interface FishRepository {

    Fish save (Fish fish);

    List<Bait> findAllByOrderByPostedAtDesc();
}
