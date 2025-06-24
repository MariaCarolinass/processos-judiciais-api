package com.tjrn.processosjudiciais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tjrn.processosjudiciais.model.Audiencia;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {
    
}
