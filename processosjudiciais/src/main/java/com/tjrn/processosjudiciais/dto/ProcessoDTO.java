package com.tjrn.processosjudiciais.dto;

import java.util.List;
import com.tjrn.processosjudiciais.model.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProcessoDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "1234567-89.2024.0.12.3456", description = "Número do processo")
    private String numProcesso;

    @Schema(example = "Vara Cível", description = "Vara do processo")
    private String vara;

    @Schema(example = "Natal", description = "Comarca do processo")
    private String comarca;

    @Schema(example = "Cobrança", description = "Assunto do processo")
    private String assunto;

    @Schema(example = "ATIVO", description = "Status do processo")
    private Status status;

    @Schema(description = "Lista de audiências vinculadas ao processo")
    private List<AudienciaDTO> audienciaList;
    
}