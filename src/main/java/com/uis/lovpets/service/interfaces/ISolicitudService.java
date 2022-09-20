package com.uis.lovpets.service.interfaces;

import com.uis.lovpets.dto.SolicitudDTO;

import java.util.List;

public interface ISolicitudService {
    List<SolicitudDTO> getAll();

    SolicitudDTO getOne(Long id);

    Boolean checkUserSolicitud(Long userId, Long publicacionId);

    Boolean crearSolicitud(SolicitudDTO solicitudDTO);

    Boolean actualizarSolicitud(SolicitudDTO solicitudDTO);

    Boolean eliminarSolicitud(Long id);

    Boolean aceptarSolicitud(Long idSolicitud);

    Boolean rechazarSolicitud(Long idSolicitud);
}
