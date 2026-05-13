package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.repositories.EmployeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {

    private final EmployeRepository repo;
    private final PasswordEncoder encoder;

    public EmployeService(EmployeRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<Employe> getAll() {
        return repo.findAll();
    }

    public Employe create(Employe e) {
        e.setPassword(encoder.encode(e.getPassword()));
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}