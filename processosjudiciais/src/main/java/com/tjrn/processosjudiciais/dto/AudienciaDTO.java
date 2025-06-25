package com.tjrn.processosjudiciais.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tjrn.processosjudiciais.model.TipoAudiencia;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AudienciaDTO {

    @Schema(example = "1")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(example = "2025-06-25", description = "Data da audiência no formato yyyy-MM-dd")
    private LocalDate data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Schema(example = "14:00:00", description = "Hora da audiência no formato HH:mm:ss")
    private LocalTime hora;

    @Schema(example = "Sala de Audiências 1", description = "Local da audiência")
    private String local;

    @Schema(example = "CONCILIACAO", description = "Tipo da audiência")
    private TipoAudiencia tipoAudiencia;

    @Schema(example = "1", description = "ID do processo relacionado")
    private Long processoId;
    
}