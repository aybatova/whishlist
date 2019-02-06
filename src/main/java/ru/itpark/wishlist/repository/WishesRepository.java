package ru.itpark.wishlist.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.wishlist.domain.Wish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class WishesRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public WishesRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, content FROM wishlist",
                (rs, i) -> new Wish(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("content")

                )
        );
    }

    public Wish findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, content FROM wishlist WHERE id = :id",
                Map.of("id", id),
                (rs, i) -> new Wish(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("content")
                )
        );
    }

    public void removeById(int id) {
        jdbcTemplate.update("DELETE FROM wishlist WHERE id = :id",
                Map.of("id", id)
                );
    }

    public void add(Wish wish) {
        jdbcTemplate.update("INSERT INTO wishlist (name, content) VALUES (:name, :content)",
                Map.of("name", wish.getName(), "content", wish.getContent())
        );
    }

    public void save(Wish wish) {
        jdbcTemplate.update("UPDATE wishlist SET name = :name, content= :content WHERE id = :id",
                Map.of("id", wish.getId(), "name", wish.getName(), "content", wish.getContent())
        );
    }
}
