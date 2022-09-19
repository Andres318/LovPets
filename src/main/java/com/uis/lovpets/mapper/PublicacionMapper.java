package com.uis.lovpets.mapper;

import com.uis.lovpets.dto.PublicacionDTO;
import com.uis.lovpets.model.Publicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicacionMapper {

    PublicacionMapper INSTANCE = Mappers.getMapper(PublicacionMapper.class);

    // Dto to Entity
    @Mapping(target = "id", source = "id")
    Publicacion toPublicacion(PublicacionDTO publicacionDTO);


    // Entity to Dto
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "direccionUsuario", source = "usuario.direccion")
    @Mapping(target = "nombreCiudadUsuario", source = "usuario.ciudad.nombre")
    @Mapping(target = "idCiudad", source = "usuario.ciudad.id")
    @Mapping(target = "telefonoUsuario", source = "usuario.telefono")
    @Mapping(target = "correoUsuario", source = "usuario.correo")
    @Mapping(target = "tipoMascota", source = "tipoMascotica.nombre")
    PublicacionDTO toPublicacionDTO(Publicacion publicacion);

}
