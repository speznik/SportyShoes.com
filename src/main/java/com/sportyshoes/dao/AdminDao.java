package com.sportyshoes.dao;

import com.sportyshoes.model.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    private final JdbcTemplate jdbc;
    public AdminDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public Admin findByUsernameAndPassword(String u, String p) {
        List<Admin> list = jdbc.query(
            "SELECT * FROM admin WHERE username = ? AND password = ?",
            new BeanPropertyRowMapper<>(Admin.class),
            u, p
        );
        return list.isEmpty() ? null : list.get(0);
    }
}
