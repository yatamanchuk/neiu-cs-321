package fishing.data;

import fishing.Bait;
import fishing.Fish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcFishRepository implements FishRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcFishRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Fish save(Fish fish) {
        fish.setId(saveFishInfo(fish));
        for(String bait : fish.getBaits()) {
            jdbc.update("insert into Fish_Baits (fish, bait) values (?,?)",
                    fish.getId(), bait);
        }
        return fish;
    }

    @Override
    public List<Bait> findAllByOrderByPostedAtDesc() {
        return null;
    }

    private long saveFishInfo(Fish fish) {
        fish.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(
                "insert into Fish (name, createdAt) values (?,?)",
                Types.VARCHAR, Types.TIMESTAMP
        );

        pscFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
             Arrays.asList(fish.getName(), Timestamp.valueOf(fish.getCreatedAt()))
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();

    }
}
