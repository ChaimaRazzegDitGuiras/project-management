package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String email;

    private String role;

    private String equipe;

    // Un employé peut avoir plusieurs tâches
    @OneToMany(mappedBy = "employe")
    private List<Tache> taches;
}