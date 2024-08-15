package com.mvc_code_generation.service;

import java.util.List;
import com.mvc_code_generation.entity.User;
import com.mvc_code_generation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public List<User> getAll() {
        return repository.findAll();
    }
    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public User save(User entity) {
        return repository.save(entity);
    }
    public User update(Long id, User entity) {
        if (repository.existsById(id)) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            return null;
        }
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
