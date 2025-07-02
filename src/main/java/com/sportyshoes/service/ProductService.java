package com.sportyshoes.service;

import com.sportyshoes.model.Product;
import com.sportyshoes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    @Autowired
    public ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> listAll() { return repo.findAll(); }
    public Product get(int id) { return repo.findById(id); }
    public void save(Product product) {
        if (product.getId() > 0) repo.update(product);
        else repo.save(product);
    }
    public void delete(int id) { repo.delete(id); }
}
