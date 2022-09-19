package com.uis.lovpets.mapper;

import com.uis.lovpets.dto.SolicitudDTO;
import com.uis.lovpets.model.Solicitud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SolicitudMapper {

    SolicitudMapper INSTANCE = Mappers.getMapper(SolicitudMapper.class);

    // Dto to Entity
    @Mapping(target = "id", source = "id")
    Solicitud toSolicitud(SolicitudDTO solicitudDTO);


    // Entity to Dto
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "direccionUsuario", source = "usuario.direccion")
    @Mapping(target = "nombreCiudadUsuario", source = "usuario.ciudad.nombre")
    @Mapping(target = "idCiudad", source = "usuario.ciudad.id")
    @Mapping(target = "telefonoUsuario", source = "usuario.telefono")
    @Mapping(target = "correoUsuario", source = "usuario.correo")
    @Mapping(target = "nombreMascota", source = "publicacion.nombre")
    @Mapping(target = "nombreUsuarioPublicacion", source = "publicacion.usuario.nombre")
    @Mapping(target = "telefonoUsuarioPublicacion", source = "publicacion.usuario.telefono")
    @Mapping(target = "correoUsuarioPublicacion", source = "publicacion.usuario.correo")
    @Mapping(target = "imagenB64", source = "publicacion.imagenB64")
    SolicitudDTO toSolicitudDTO(Solicitud solicitud);
}
