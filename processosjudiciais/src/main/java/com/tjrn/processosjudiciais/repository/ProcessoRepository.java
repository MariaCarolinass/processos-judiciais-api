package com.tjrn.processosjudiciais.repository;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tjrn.processosjudiciais.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    
    List<Processo> findByStatus(Status status);

    List<Processo> findByComarca(String comarca);
    
    List<Processo> findByStatusAndComarca(Status status, String comarca);

}
