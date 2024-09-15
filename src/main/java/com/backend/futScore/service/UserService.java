package com.backend.futScore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.futScore.models.User;
import com.backend.futScore.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Id " + id + " não encontrado"));
    }

    public User create(User obj) {
        obj.setId(null);
        return this.userRepository.save(obj);
    }
}
