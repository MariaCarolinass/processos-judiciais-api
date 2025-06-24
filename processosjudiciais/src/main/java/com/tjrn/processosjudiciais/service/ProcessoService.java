package com.tjrn.processosjudiciais.service;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tjrn.processosjudiciais.model.Processo;
import com.tjrn.processosjudiciais.repository.ProcessoRepository;

@Service
public class ProcessoService {

    private final ProcessoRepository repository;

    public ProcessoService(ProcessoRepository repository) {
        this.repository = repository;
    }

    public List<Processo> findAll(Status status, String comarca) {
        if (status != null && comarca != null) {
            return repository.findByStatusAndComarca(status, comarca);
        } else if (status != null) {
            return repository.findByStatus(status);
        } else if (comarca != null) {
            return repository.findByComarca(comarca);
        } else {
            return repository.findAll();
        }
    }

    public List<Processo> findByStatusAndComarca(Status status, String comarca) {
        return repository.findByStatusAndComarca(status, comarca);
    }

    public List<Processo> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public List<Processo> findByComarca(String comarca) {
        return repository.findByComarca(comarca);
    }

    public List<Processo> findAll() {
        return repository.findAll();
    }
    
    public Processo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Processo não encontrado com o ID: " + id));
    }
    
    public Processo save(Processo processo) {
        String regex = "^\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}$";
        if (!processo.getNumProcesso().matches(regex)) {
            throw new IllegalArgumentException("Número de processo inválido. Formato esperado: 1234567-89.2024.0.12.3456");
        }
        return repository.save(processo);
    }

}
