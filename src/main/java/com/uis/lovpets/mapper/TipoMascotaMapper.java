package com.uis.lovpets.mapper;

import com.uis.lovpets.dto.TipoMascotaDTO;
import com.uis.lovpets.model.TipoMascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoMascotaMapper {

    TipoMascotaMapper INSTANCE = Mappers.getMapper(TipoMascotaMapper.class);

    // Dto to Entity
    @Mapping(target = "id", source = "id")
    TipoMascota toTipoMascota(TipoMascotaDTO userDTO);


    // Entity to Dto
    @Mapping(target = "id", source = "id")
    TipoMascotaDTO toTipoMascotaDTO(TipoMascota user);

}
