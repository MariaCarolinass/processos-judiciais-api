package com.tjrn.processosjudiciais.service;

import com.tjrn.processosjudiciais.model.Status;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.model.Processo;
import com.tjrn.processosjudiciais.repository.ProcessoRepository;
import jakarta.transaction.Transactional;

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
    
    @Transactional
    public Processo save(Processo processo) {
        if (repository.existsByNumProcesso(processo.getNumProcesso())) {
            throw new IllegalArgumentException("Já existe um processo com esse número.");
        }
        
        if (processo.getId() != null) {
            Processo existente = repository.findById(processo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Processo não encontrado com o ID: " + processo.getId()));

            existente.setNumProcesso(processo.getNumProcesso());
            existente.setVara(processo.getVara());
            existente.setComarca(processo.getComarca());
            existente.setAssunto(processo.getAssunto());
            existente.setStatus(processo.getStatus());

            existente.getAudienciaList().removeIf(audExistente -> 
                processo.getAudienciaList() == null || processo.getAudienciaList().stream()
                    .noneMatch(audRecebida -> audRecebida.getId() != null && audRecebida.getId().equals(audExistente.getId()))
            );

            if (processo.getAudienciaList() != null) {
                for (Audiencia audRecebida : processo.getAudienciaList()) {
                    if (audRecebida.getId() == null) {
                        audRecebida.setProcesso(existente);
                        existente.getAudienciaList().add(audRecebida);
                    } else {
                        Audiencia audExistente = existente.getAudienciaList().stream()
                            .filter(aud -> aud.getId().equals(audRecebida.getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Audiência não encontrada com o ID: " + audRecebida.getId()));

                        audExistente.setData(audRecebida.getData());
                        audExistente.setHora(audRecebida.getHora());
                        audExistente.setLocal(audRecebida.getLocal());
                        audExistente.setTipoAudiencia(audRecebida.getTipoAudiencia());
                    }
                }
            }

            return repository.save(existente);
        } else {
            if (processo.getAudienciaList() != null) {
                for (Audiencia aud : processo.getAudienciaList()) {
                    aud.setProcesso(processo);
                    aud.setId(null);
                }
            }
            return repository.save(processo);
        }
    }

}
