package com.sportyshoes.dao;

import com.sportyshoes.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbc;
    public UserDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int save(User u) {
        return jdbc.update(
            "INSERT INTO user(email, full_name, password) VALUES (?,?,?)",
            u.getEmail(), u.getFullName(), u.getPassword()
        );
    }

    public User findByEmailAndPassword(String email, String pass) {
        List<User> list = jdbc.query(
            "SELECT * FROM user WHERE email = ? AND password = ?",
            new BeanPropertyRowMapper<>(User.class),
            email, pass
        );
        return list.isEmpty() ? null : list.get(0);
    }

    // ← This method must exist for admin/customers
    public List<User> findAll() {
        return jdbc.query(
            "SELECT * FROM user",
            new BeanPropertyRowMapper<>(User.class)
        );
    }
}
