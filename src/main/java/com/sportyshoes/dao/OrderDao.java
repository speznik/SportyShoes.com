package com.sportyshoes.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDao {
    private final JdbcTemplate jdbc;
    public OrderDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int saveOrder(int userId, BigDecimal total) {
        jdbc.update(
            "INSERT INTO orders (user_id, total_amount) VALUES (?, ?)",
            userId, total
        );
        return jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public void saveItem(int orderId, int productId, int qty) {
        jdbc.update(
            "INSERT INTO order_item (order_id, product_id, quantity) VALUES (?, ?, ?)",
            orderId, productId, qty
        );
    }

    // ← This method must exist for admin/reports
    public List<Map<String,Object>> findReports() {
        return jdbc.queryForList(
            "SELECT o.order_date AS date, u.full_name AS customer, o.total_amount AS total " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.id " +
            "ORDER BY o.order_date DESC"
        );
    }
}
