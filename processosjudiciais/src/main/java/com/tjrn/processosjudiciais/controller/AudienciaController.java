package com.tjrn.processosjudiciais.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tjrn.processosjudiciais.dto.AudienciaDTO;
import com.tjrn.processosjudiciais.mapper.AudienciaMapper;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.model.Processo;
import com.tjrn.processosjudiciais.repository.ProcessoRepository;
import com.tjrn.processosjudiciais.service.AudienciaService;

@RestController
@RequestMapping("/api/v1/audiencias")
public class AudienciaController {

    @Autowired
    private final AudienciaService service;

    @Autowired
    private AudienciaMapper mapper;

    @Autowired
    private ProcessoRepository repository;

    public AudienciaController(AudienciaService audienciaService) {
        this.service = audienciaService;
    }

    @GetMapping
    public List<Audiencia> listarTodasAudiencias() {
        return service.findAll();
    }

    @GetMapping("/agenda")
    public List<Audiencia> listarAgendaPorComarcaEData(
            @RequestParam String comarca, 
            @RequestParam LocalDate data) {
        return service.findByProcessoComarcaAndData(comarca, data);
    }

    @PostMapping
    public ResponseEntity<AudienciaDTO> adicionarAudiencia(@RequestBody AudienciaDTO dto) {
        Audiencia entity = mapper.toEntity(dto);

        Processo processo = repository.findById(dto.getProcessoId())
            .orElseThrow(() -> new IllegalArgumentException("Processo com ID " + dto.getProcessoId() + " não encontrado."));
        entity.setProcesso(processo);

        Audiencia salvo = service.save(entity);
        return ResponseEntity.ok(mapper.toDto(salvo));
    }

}
