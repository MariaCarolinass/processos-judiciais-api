package com.tjrn.processosjudiciais.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tjrn.processosjudiciais.model.Audiencia;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {
    
    @Query("SELECT a FROM Audiencia a WHERE a.processo.comarca = :comarca AND a.data = :data")
    List<Audiencia> findByProcessoComarcaAndData(String comarca, LocalDate data);
    
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Audiencia a WHERE a.local = :local AND a.processo.vara = :vara")
    boolean existsByLocalAndProcessoVara(String local, String vara);

}
