package com.example.demo.controllers;

import com.example.demo.entities.Employe;
import com.example.demo.services.EmployeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin("*")
public class EmployeController {

    private final EmployeService service;

    public EmployeController(EmployeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employe> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Employe create(@RequestBody Employe e) {
        return service.create(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}