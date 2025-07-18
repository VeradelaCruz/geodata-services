package com.example.geologist_service.mapper;


import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.models.Geologist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeologistMapper {
    GeologistYearsExperienceDTO toDto(Geologist geologist);
}
