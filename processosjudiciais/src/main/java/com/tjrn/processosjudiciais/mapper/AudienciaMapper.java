package com.tjrn.processosjudiciais.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.tjrn.processosjudiciais.dto.AudienciaDTO;
import com.tjrn.processosjudiciais.model.Audiencia;

@Mapper(componentModel = "spring")
public interface AudienciaMapper {

    @Mapping(source = "processo.id", target = "processoId")
    AudienciaDTO toDto(Audiencia entity);

    @Mapping(target = "processo", ignore = true)
    Audiencia toEntity(AudienciaDTO dto);
    
}