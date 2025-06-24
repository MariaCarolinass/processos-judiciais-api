package com.tjrn.processosjudiciais.repository;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tjrn.processosjudiciais.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    @Query("SELECT p FROM Processo p WHERE p.status = :status")
    List<Processo> findByStatus(Status status);

    @Query("SELECT p FROM Processo p WHERE p.comarca = :comarca")
    List<Processo> findByComarca(String comarca);
    
    @Query("SELECT p FROM Processo p WHERE p.status = :status AND p.comarca = :comarca")
    List<Processo> findByStatusAndComarca(Status status, String comarca);

}
