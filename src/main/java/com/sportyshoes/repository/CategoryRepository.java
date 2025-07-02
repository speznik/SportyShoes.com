package com.sportyshoes.repository;

import com.sportyshoes.model.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    public CategoryRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    public Category findById(int id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), id);
    }

    public int save(Category category) {
        String sql = "INSERT INTO category(name) VALUES(?)";
        return jdbcTemplate.update(sql, category.getName());
    }

    public int update(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, category.getName(), category.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
