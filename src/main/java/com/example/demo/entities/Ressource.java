package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String type;

    private Double cout;

    private Boolean disponibilite;

    // Plusieurs ressources peuvent être utilisées dans plusieurs tâches
    @ManyToMany(mappedBy = "ressources")
    private List<Tache> taches;
}