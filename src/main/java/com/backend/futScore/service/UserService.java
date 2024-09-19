package com.backend.futScore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.futScore.models.User;
import com.backend.futScore.repository.UserRepository;
import com.backend.futScore.security.Token;
import com.backend.futScore.security.TokenUtil;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Id " + id + " não encontrado"));
    }

    public User create(User obj) {
        String encodedPassword = this.passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encodedPassword);
        obj.setId(null);
        return this.userRepository.save(obj);
    }

    public Boolean validarSenha(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            return false;
        }
        return passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
    }

    public Token gerarToken(User user) {
        User usuarioBanco = userRepository.findByEmail(user.getEmail());
        if (usuarioBanco != null) {
            Boolean valid = passwordEncoder.matches(user.getPassword(), usuarioBanco.getPassword());
            if (valid) {
                return new Token(TokenUtil.createToken(user));
            }
        }
        return null;
    }
}
