package com.tjrn.processosjudiciais.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tjrn.processosjudiciais.model.Audiencia;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {
    
    List<Audiencia> findByProcessoComarcaAndData(String comarca, LocalDate data);

    boolean existsByLocalAndProcessoVara(String local, String vara);

}
