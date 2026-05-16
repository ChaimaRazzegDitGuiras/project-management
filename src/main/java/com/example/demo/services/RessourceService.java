package com.example.demo.services;

import com.example.demo.entities.Ressource;
import com.example.demo.exceptions.RessourceNotFoundException;
import com.example.demo.repositories.RessourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceService {

    private final RessourceRepository repo;

    public RessourceService(RessourceRepository repo) {
        this.repo = repo;
    }

    public List<Ressource> getAll() {
        return repo.findAll();
    }

    public Ressource getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Ressource not found with id: " + id));
    }

    public Ressource create(Ressource r) {
        return repo.save(r);
    }

    public Ressource update(Long id, Ressource updated) {
        Ressource ressource = getById(id);
        
        ressource.setNom(updated.getNom());
        ressource.setType(updated.getType());
        
        return repo.save(ressource);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RessourceNotFoundException("Ressource not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
