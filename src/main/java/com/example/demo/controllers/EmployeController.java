package com.example.demo.controllers;

import com.example.demo.entities.Employe;
import com.example.demo.services.EmployeService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeService service;

    public EmployeController(EmployeService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Employe> getAll() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employe create(@RequestBody Employe e) {
        return service.create(e);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employe getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employe updateById(@PathVariable Long id,
                              @RequestBody Employe e) {
        return service.update(id, e);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public Employe getMyProfile(Authentication auth) {
        String email = auth.getName();
        return service.getByEmail(email);
    }

    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    public Employe updateMyProfile(Authentication auth,
                                   @RequestBody Employe e) {
        String email = auth.getName();
        return service.updateByEmail(email, e);
    }
}