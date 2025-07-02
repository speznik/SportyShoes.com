package com.sportyshoes.repository;

import com.sportyshoes.model.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;
    public AdminRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Admin findByUsername(String username) {
        String sql = "SELECT * FROM admin WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), username);
    }

    public int updatePassword(int id, String newPassword) {
        String sql = "UPDATE admin SET password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, newPassword, id);
    }
}
