package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotNull(message = "Le projet est obligatoire")
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    private String description;

    @Enumerated(EnumType.STRING)
    private EtatTache etat = EtatTache.A_FAIRE;

    @Enumerated(EnumType.STRING)
    private PrioriteTache priorite = PrioriteTache.MOYENNE;

    private LocalDate deadline;

    @ManyToMany
    @JoinTable(
            name = "tache_ressource",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "ressource_id")
    )
    private List<Ressource> ressources;

    @Transient
    private Long employeId;

    @Transient
    private Long projetId;
}