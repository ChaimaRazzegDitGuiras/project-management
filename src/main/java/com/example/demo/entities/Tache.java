package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Plusieurs tâches appartiennent à un projet
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    // Plusieurs tâches peuvent être assignées à un employé
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    private String description;

    private String etat;

    private String priorite;

    private LocalDate deadline;

    // Une tâche peut utiliser plusieurs ressources
    @ManyToMany
    @JoinTable(
            name = "tache_ressource",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "ressource_id")
    )
    private List<Ressource> ressources;
}