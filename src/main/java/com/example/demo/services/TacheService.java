package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.exceptions.TacheNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {

    private final TacheRepository repo;
    private final EmployeRepository employeRepo;
    private final ProjetRepository projetRepo;

    public TacheService(
            TacheRepository repo,
            EmployeRepository employeRepo,
            ProjetRepository projetRepo
    ) {
        this.repo = repo;
        this.employeRepo = employeRepo;
        this.projetRepo = projetRepo;
    }

    public List<Tache> getAll() {
        return repo.findAll();
    }

    public List<Tache> getByEmployeEmail(String email) {
        return repo.findByEmployeEmail(email);
    }

    public Tache getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new TacheNotFoundException("Tache not found"));
    }

    public Tache create(Tache t) {

        if (t.getEmployeId() == null || t.getProjetId() == null) {
            throw new RuntimeException("employeId et projetId obligatoires");
        }

        Employe emp = employeRepo.findById(t.getEmployeId())
                .orElseThrow(() -> new UserNotFoundException("Employe not found"));

        Projet projet = projetRepo.findById(t.getProjetId())
                .orElseThrow(() -> new RuntimeException("Projet not found"));

        t.setEmploye(emp);
        t.setProjet(projet);

        if (t.getEtat() == null) {
            t.setEtat(EtatTache.A_FAIRE);
        }

        if (t.getAssignments() != null) {
            t.getAssignments().forEach(a -> {
                a.setTache(t);
                if (a.getId() == null) {
                    a.setId(new TacheRessourceId());
                }
                a.getId().setTacheId(t.getId());
                a.getId().setRessourceId(a.getRessource().getId());
            });
        }

        return repo.save(t);
    }

    public Tache updateForEmploye(String email, Long id, Tache updated) {

        Tache tache = getById(id);

        if (tache.getEmploye() == null ||
            !tache.getEmploye().getEmail().equals(email)) {
            throw new RuntimeException("Not allowed");
        }

        tache.setDescription(updated.getDescription());

        if (updated.getEtat() != null) {
            tache.setEtat(updated.getEtat());
        }

        return repo.save(tache);
    }

    public Tache update(Long id, Tache updated) {

        Tache tache = getById(id);

        tache.setTitle(updated.getTitle());
        tache.setDescription(updated.getDescription());
        tache.setEtat(updated.getEtat());
        tache.setPriorite(updated.getPriorite());
        tache.setDeadline(updated.getDeadline());

        if (updated.getEmploye() != null) {
            Employe emp = employeRepo.findById(updated.getEmploye().getId())
                    .orElseThrow(() -> new UserNotFoundException("Employe not found"));
            tache.setEmploye(emp);
        }

        if (updated.getProjet() != null) {
            Projet p = projetRepo.findById(updated.getProjet().getId())
                    .orElseThrow(() -> new RuntimeException("Projet not found"));
            tache.setProjet(p);
        }

        // Update assignments
        if (updated.getAssignments() != null) {
            tache.getAssignments().clear();
            updated.getAssignments().forEach(a -> {
                a.setTache(tache);
                if (a.getId() == null) {
                    a.setId(new TacheRessourceId());
                }
                a.getId().setTacheId(tache.getId());
                a.getId().setRessourceId(a.getRessource().getId());
                tache.getAssignments().add(a);
            });
        }

        return repo.save(tache);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}