package com.example.demo.repositories;

import com.example.demo.entities.Employe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
	Optional<Employe> findById(Long id);
	 Optional<Employe> findByEmail(String email);
}