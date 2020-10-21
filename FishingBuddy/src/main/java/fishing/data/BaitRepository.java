package fishing.data;

import fishing.Bait;

import java.util.List;

public interface BaitRepository {

    List<Bait> findAll();

    Bait findOne(String id);

    Bait save(Bait bait);
}
