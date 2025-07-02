package com.sportyshoes.service;

import com.sportyshoes.model.Category;
import com.sportyshoes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;
    @Autowired
    public CategoryService(CategoryRepository repo) { this.repo = repo; }

    public List<Category> listAll() { return repo.findAll(); }
    public Category get(int id) { return repo.findById(id); }
    public void save(Category category) {
        if (category.getId() > 0) repo.update(category);
        else repo.save(category);
    }
    public void delete(int id) { repo.delete(id); }
}
