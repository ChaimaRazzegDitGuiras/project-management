package com.example.demo.repositories;

import com.example.demo.entities.Projet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
	Optional<Projet> findByNom(String nom);
}