package com.sportyshoes.dao;

import com.sportyshoes.model.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao {
    private final JdbcTemplate jdbc;
    public CategoryDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Category> findAll() {
        return jdbc.query(
            "SELECT * FROM category",
            new BeanPropertyRowMapper<>(Category.class)
        );
    }

    public int save(Category c) {
        if (c.getId() > 0) {
            return jdbc.update(
                "UPDATE category SET name = ? WHERE id = ?",
                c.getName(), c.getId()
            );
        } else {
            return jdbc.update(
                "INSERT INTO category(name) VALUES(?)",
                c.getName()
            );
        }
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM category WHERE id = ?", id);
    }
}
