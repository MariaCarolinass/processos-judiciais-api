package com.tjrn.processosjudiciais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.tjrn.processosjudiciais.dto.ProcessoDTO;
import com.tjrn.processosjudiciais.model.Processo;

@Mapper(componentModel = "spring", uses = {AudienciaMapper.class})
public interface ProcessoMapper {

    ProcessoDTO toDto(Processo entity);

    @Mapping(target = "audienciaList", source = "audienciaList")
    Processo toEntity(ProcessoDTO dto);
    
}