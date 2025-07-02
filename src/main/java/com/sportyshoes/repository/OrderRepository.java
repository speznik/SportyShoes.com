package com.sportyshoes.repository;

import com.sportyshoes.model.Order;
import com.sportyshoes.model.OrderItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbc;
    public OrderRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public int save(Order o) {
        return jdbc.update(
          "INSERT INTO `order`(user_id,total_amount) VALUES(?,?)",
          o.getUserId(), o.getTotalAmount()
        );
    }
    public List<OrderItem> findItems(int orderId) {
        return jdbc.query(
          "SELECT * FROM order_item WHERE order_id = ?",
          new BeanPropertyRowMapper<>(OrderItem.class),
          orderId
        );
    }
}
