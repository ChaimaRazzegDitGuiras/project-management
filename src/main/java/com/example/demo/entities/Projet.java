package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom du projet est obligatoire")
    private String nom;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Double budget;

    private String statut;

    // Un projet contient plusieurs tâches
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tache> taches;
}