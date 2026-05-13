package com.example.demo.services;

import com.example.demo.entities.Projet;
import com.example.demo.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public List<Projet> getAll() {
        return projetRepository.findAll();
    }

    public Projet getById(Long id) {
        return projetRepository.findById(id).orElse(null);
    }

    public Projet save(Projet projet) {
        return projetRepository.save(projet);
    }

    public Projet update(Long id, Projet projet) {
        projet.setId(id);
        return projetRepository.save(projet);
    }

    public void delete(Long id) {
        projetRepository.deleteById(id);
    }
}