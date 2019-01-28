package ru.itpark.wishlist.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.wishlist.domain.Wish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WishesRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WishesRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, content FROM wishlist",
                new RowMapper<Wish>() {
                    @Override
                    public Wish mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Wish(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("content")
                        );
                    }
                }
        );
    }
}
