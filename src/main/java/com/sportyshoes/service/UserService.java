package com.sportyshoes.service;

import com.sportyshoes.model.User;
import com.sportyshoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    @Autowired
    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> listAll() { return repo.findAll(); }
    public List<User> search(String keyword) { return repo.search(keyword); }
}
