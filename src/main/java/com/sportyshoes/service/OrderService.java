package com.sportyshoes.service;

import com.sportyshoes.model.Order;
import com.sportyshoes.model.OrderItem;
import com.sportyshoes.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo) { this.repo = repo; }

    public void placeOrder(Order o)   { repo.save(o); }
    public List<OrderItem> getItems(int orderId) { return repo.findItems(orderId); }
}
