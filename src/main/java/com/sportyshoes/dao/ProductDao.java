package com.sportyshoes.dao;

import com.sportyshoes.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbc;
    public ProductDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Product> findAll() {
        return jdbc.query(
            "SELECT * FROM product",
            new BeanPropertyRowMapper<>(Product.class)
        );
    }

    public Product findById(int id) {
        return jdbc.queryForObject(
            "SELECT * FROM product WHERE id = ?",
            new BeanPropertyRowMapper<>(Product.class),
            id
        );
    }

    public int saveOrUpdate(Product p) {
        if (p.getId() > 0) {
            return jdbc.update(
                "UPDATE product SET name=?, description=?, price=?, image_url=? WHERE id=?",
                p.getName(), p.getDescription(), p.getPrice(), p.getImageUrl(), p.getId()
            );
        } else {
            return jdbc.update(
                "INSERT INTO product(name, description, price, image_url) VALUES(?,?,?,?)",
                p.getName(), p.getDescription(), p.getPrice(), p.getImageUrl()
            );
        }
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM product WHERE id = ?", id);
    }
}
