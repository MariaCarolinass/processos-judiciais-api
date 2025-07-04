package com.tjrn.processosjudiciais.controller;

import java.util.List;
import com.tjrn.processosjudiciais.model.Status;
import com.tjrn.processosjudiciais.dto.ProcessoDTO;
import com.tjrn.processosjudiciais.mapper.ProcessoMapper;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.model.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tjrn.processosjudiciais.service.ProcessoService;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {
    
    @Autowired
    private final ProcessoService service;

    @Autowired
    private ProcessoMapper mapper;

    public ProcessoController(ProcessoService processoService) {
        this.service = processoService;
    }

    @GetMapping
    public List<Processo> listarProcessos(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String comarca) {
        if (status != null && comarca != null) {
            return service.findByStatusAndComarca(status, comarca);
        } else if (status != null) {
            return service.findByStatus(status);
        } else if (comarca != null) {
            return service.findByComarca(comarca);
        } else {
            return service.findAll();
        }
    }

    @PostMapping
    public ResponseEntity<ProcessoDTO> adicionarProcesso(@RequestBody ProcessoDTO dto) {
        dto.setId(null);  
        Processo entity = mapper.toEntity(dto);
        if (entity.getAudienciaList() != null) {
            for (Audiencia aud : entity.getAudienciaList()) {
                aud.setProcesso(entity);
            }
        }
        Processo salvo = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(salvo));
    }

}
