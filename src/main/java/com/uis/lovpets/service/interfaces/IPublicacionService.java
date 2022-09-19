package com.uis.lovpets.service.interfaces;

import com.uis.lovpets.dto.PublicacionDTO;

import java.util.List;

public interface IPublicacionService {
    Boolean crearPublicacion(PublicacionDTO publicacionDTO);

    List<PublicacionDTO> getAll();

    PublicacionDTO getOne(Long id);

    Boolean actualizarPublicacion(PublicacionDTO publicacionDTO);

    Boolean eliminarPublicacion(Long id);
}
