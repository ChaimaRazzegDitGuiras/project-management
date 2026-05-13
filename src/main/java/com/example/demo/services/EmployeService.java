package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.exceptions.UserNotFoundException;
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

    public Employe getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employe not found"));
    }

    public Employe update(Long id, Employe updated) {

        Employe existing = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employe not found"));

        applyUpdates(existing, updated);

        return repo.save(existing);
    }

    public Employe getByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Employe not found"));
    }

    public Employe updateByEmail(String email, Employe updated) {

        Employe existing = repo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Employe not found"));

        applyUpdatesSelf(existing, updated);

        return repo.save(existing);
    }

    private void applyUpdates(Employe existing, Employe updated) {

        existing.setNom(updated.getNom());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        existing.setEquipe(updated.getEquipe());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(encoder.encode(updated.getPassword()));
        }
    }

    private void applyUpdatesSelf(Employe existing, Employe updated) {

        existing.setNom(updated.getNom());
        existing.setEquipe(updated.getEquipe());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(encoder.encode(updated.getPassword()));
        }
    }
}