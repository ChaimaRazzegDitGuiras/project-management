package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Double budget;

    private String statut;

    // Un projet contient plusieurs tâches
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Tache> taches;
}