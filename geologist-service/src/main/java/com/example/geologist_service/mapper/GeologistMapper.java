package com.example.geologist_service.mapper;


import com.example.geologist_service.dtos.GeologistDTO;
import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.models.Geologist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeologistMapper {
    GeologistYearsExperienceDTO toDto(Geologist geologist);

    GeologistDTO toSimpleDto(Geologist geologist);

    List<GeologistDTO> toSimpleDtoList(List<Geologist> geologists);
}

