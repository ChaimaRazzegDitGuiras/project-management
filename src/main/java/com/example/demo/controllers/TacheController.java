package com.example.demo.controllers;

import com.example.demo.entities.Tache;
import com.example.demo.services.TacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private final TacheService service;

    public TacheController(TacheService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tache> getAll() {
        return service.getAll();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public List<Tache> getMyTasks(Authentication auth) {
        return service.getByEmployeEmail(auth.getName());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Tache create(@RequestBody Tache t) {
        return service.create(t);
    }

    @PutMapping("/my/{id}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public Tache updateMyTask(Authentication auth,
                              @PathVariable Long id,
                              @RequestBody Tache t) {
        return service.updateForEmploye(auth.getName(), id, t);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Tache update(@PathVariable Long id,
                        @RequestBody Tache t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}