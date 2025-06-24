package com.tjrn.processosjudiciais.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tjrn.processosjudiciais.model.Audiencia;
import com.tjrn.processosjudiciais.service.AudienciaService;

@RestController
@RequestMapping("/api/v1/audiencias")
public class AudienciaController {

    private final AudienciaService service;

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
    public Audiencia adicionarAudiencia(@RequestBody Audiencia audiencia) {
        return service.save(audiencia);
    }

}
