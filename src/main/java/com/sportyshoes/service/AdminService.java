package com.sportyshoes.service;

import com.sportyshoes.model.Admin;
import com.sportyshoes.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository repo;
    @Autowired
    public AdminService(AdminRepository repo) { this.repo = repo; }

    public Admin authenticate(String username, String password) {
        Admin a = repo.findByUsername(username);
        if (a != null && a.getPassword().equals(password)) {
            return a;
        }
        return null;
    }

    public boolean changePassword(int adminId, String newPassword) {
        return repo.updatePassword(adminId, newPassword) > 0;
    }
}
