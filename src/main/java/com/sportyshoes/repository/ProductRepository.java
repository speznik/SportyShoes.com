package com.sportyshoes.repository;

import com.sportyshoes.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public int save(Product p) {
        String sql = "INSERT INTO product(name,description,price,category_id,image_url) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, p.getName(), p.getDescription(), p.getPrice(), p.getCategoryId(), p.getImageUrl());
    }

    public int update(Product p) {
        String sql = "UPDATE product SET name=?,description=?,price=?,category_id=?,image_url=? WHERE id=?";
        return jdbcTemplate.update(sql, p.getName(), p.getDescription(), p.getPrice(), p.getCategoryId(), p.getImageUrl(), p.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
