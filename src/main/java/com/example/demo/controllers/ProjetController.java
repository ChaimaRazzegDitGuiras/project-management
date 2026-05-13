package com.example.demo.controllers;

import com.example.demo.entities.Projet;
import com.example.demo.services.ProjetService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    // GET ALL
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Projet> getAll() {
        return projetService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Projet getById(@PathVariable Long id) {
        return projetService.getById(id);
    }

    // CREATE
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Projet save(@Valid @RequestBody Projet projet) {
        return projetService.save(projet);
    }


    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Projet update(@PathVariable Long id,
                         @Valid @RequestBody Projet projet) {
        return projetService.update(id, projet);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        projetService.delete(id);
    }
}