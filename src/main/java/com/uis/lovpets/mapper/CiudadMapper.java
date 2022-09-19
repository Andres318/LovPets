package com.uis.lovpets.mapper;

import com.uis.lovpets.dto.CiudadDTO;
import com.uis.lovpets.model.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CiudadMapper {

    CiudadMapper INSTANCE = Mappers.getMapper(CiudadMapper.class);

    // Dto to Entity
    @Mapping(target = "id", source = "id")
    Ciudad toCiudad(CiudadDTO userDTO);


    // Entity to Dto
    @Mapping(target = "id", source = "id")
    CiudadDTO toCiudadDTO(Ciudad user);

}
