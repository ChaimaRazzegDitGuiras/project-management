package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TacheRessource> assignments;
}