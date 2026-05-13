package com.example.demo.controllers;

import com.example.demo.entities.Projet;
import com.example.demo.services.ProjetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin("*")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public List<Projet> getAll() {
        return projetService.getAll();
    }

    @GetMapping("/{id}")
    public Projet getById(@PathVariable Long id) {
        return projetService.getById(id);
    }

    @PostMapping
    public Projet save(@RequestBody Projet projet) {
        return projetService.save(projet);
    }

    @PutMapping("/{id}")
    public Projet update(@PathVariable Long id,
                         @RequestBody Projet projet) {
        return projetService.update(id, projet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projetService.delete(id);
    }
}