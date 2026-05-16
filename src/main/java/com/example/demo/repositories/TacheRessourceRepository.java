package com.example.demo.repositories;

import com.example.demo.entities.TacheRessource;
import com.example.demo.entities.TacheRessourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRessourceRepository extends JpaRepository<TacheRessource, TacheRessourceId> {
}
