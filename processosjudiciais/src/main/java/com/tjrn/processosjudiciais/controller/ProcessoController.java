package com.tjrn.processosjudiciais.controller;

import java.util.List;
import java.io.ObjectInputFilter.Status;
import com.tjrn.processosjudiciais.model.Processo;
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
    
    private final ProcessoService service;

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
    public Processo adicionarProcesso(@RequestBody Processo processo) {
        return service.save(processo);
    }

}
