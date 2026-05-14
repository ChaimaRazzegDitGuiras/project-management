package com.example.demo.repositories;

import com.example.demo.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {

    List<Tache> findByEmployeEmail(String email);
}