package fishing.data;

import fishing.Bait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcBaitRepository implements BaitRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcBaitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Bait> findAll() {
        return jdbc.query("select id, name, type from Bait", this::mapRowToBait);
    }

    private Bait mapRowToBait(ResultSet resultSet, int rowNum) throws SQLException {
        return new Bait (
                resultSet.getString("id"),
                resultSet.getString("name"),
                Bait.Type.valueOf(resultSet.getString("type"))
        );
    }

    @Override
    public Bait findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Bait where id=?", this::mapRowToBait, id);
    }

    @Override
    public Bait save(Bait bait) {
        jdbc.update(
                "insert into Bait (id, name, type) values (?,?,?)",
                bait.getId(),
                bait.getName(),
                bait.getType().toString()
        );
        return bait;
    }
}
