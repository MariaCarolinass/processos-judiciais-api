package com.tjrn.processosjudiciais.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.model.Status;
import com.tjrn.processosjudiciais.repository.AudienciaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AudienciaService {

    private final AudienciaRepository repository;

    public AudienciaService(AudienciaRepository repository) {
        this.repository = repository;
    }

    public List<Audiencia> findByProcessoComarcaAndData(String comarca, LocalDate data) {
        return repository.findByProcessoComarcaAndData(comarca, data);
    }

    public List<Audiencia> findAll() {
        return repository.findAll();
    }
    
    @Transactional
    public Audiencia save(Audiencia audiencia) {
        if (audiencia.getProcesso() == null || audiencia.getProcesso().getId() == null) {
            throw new IllegalArgumentException("Audiência deve estar vinculada a um processo existente.");
        }

        boolean isValid = repository.existsByLocalAndProcessoVara(
            audiencia.getLocal(), audiencia.getProcesso().getVara());

        DayOfWeek diaSemana = audiencia.getData().getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Audiências só podem ser marcadas em dias úteis (segunda a sexta-feira).");
        }

        if (isValid) {
            throw new IllegalArgumentException("Já existe uma audiência agendada para este local e vara.");
        }

        if (audiencia.getProcesso().getStatus() == Status.ARQUIVADO) {
            throw new IllegalArgumentException("Não é possível agendar audiência para processo arquivado.");
        }

        if (audiencia.getProcesso().getStatus() == Status.SUSPENSO) {
            throw new IllegalArgumentException("Não é possível agendar audiência para processo suspenso.");
        }

         Audiencia managedAudiencia;

        if (audiencia.getId() != null) {
            managedAudiencia = repository.findById(audiencia.getId())
                .orElseThrow(() -> new EntityNotFoundException("Audiência não encontrada para atualização."));
            managedAudiencia.setData(audiencia.getData());
            managedAudiencia.setHora(audiencia.getHora());
            managedAudiencia.setLocal(audiencia.getLocal());
            managedAudiencia.setTipoAudiencia(audiencia.getTipoAudiencia());
            managedAudiencia.setProcesso(audiencia.getProcesso());
        } else {
            managedAudiencia = audiencia;
        }

        return repository.save(audiencia);
    }

}
