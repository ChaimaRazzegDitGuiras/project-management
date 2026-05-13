package com.example.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employe;
import com.example.demo.exceptions.BadCredentialsException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.security.JwtUtil;

@Service
public class AuthService {

    private final EmployeRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(EmployeRepository repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {

        Employe user = repo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Password incorrect");
        }

        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}