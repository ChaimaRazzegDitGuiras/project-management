package com.example.demo.services;

import com.example.demo.entities.Projet;
import com.example.demo.exceptions.ProjetAlreadyExistsException;
import com.example.demo.exceptions.ProjetNotFoundException;
import com.example.demo.repositories.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return projetRepository.findById(id)
                .orElseThrow(() ->
                        new ProjetNotFoundException("Projet not found"));
    }

    public Projet save(Projet projet) {

        if (projetRepository.findByNom(projet.getNom()).isPresent()) {
            throw new ProjetAlreadyExistsException("Projet already exists");
        }

        return projetRepository.save(projet);
    }

    public Projet update(Long id, Projet projet) {

        Projet existing = projetRepository.findById(id)
                .orElseThrow(() ->
                        new ProjetNotFoundException("Projet not found"));

        Optional<Projet> projetWithSameName =
                projetRepository.findByNom(projet.getNom());

        if (projetWithSameName.isPresent()
                && !projetWithSameName.get().getId().equals(id)) {

            throw new ProjetAlreadyExistsException("Projet already exists");
        }

        existing.setNom(projet.getNom());
        existing.setDateDebut(projet.getDateDebut());
        existing.setDateFin(projet.getDateFin());
        existing.setBudget(projet.getBudget());
        existing.setStatut(projet.getStatut());

        return projetRepository.save(existing);
    }

    public void delete(Long id) {

        Projet existing = projetRepository.findById(id)
                .orElseThrow(() ->
                        new ProjetNotFoundException("Projet not found"));

        projetRepository.delete(existing);
    }
}