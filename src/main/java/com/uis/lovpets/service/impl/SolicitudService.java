package com.uis.lovpets.service.impl;

import com.uis.lovpets.dto.SolicitudDTO;
import com.uis.lovpets.mapper.SolicitudMapper;
import com.uis.lovpets.model.Publicacion;
import com.uis.lovpets.model.Solicitud;
import com.uis.lovpets.repository.IPublicacionRepository;
import com.uis.lovpets.repository.ISolicitudRepository;
import com.uis.lovpets.service.interfaces.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudService implements ISolicitudService {

    private ISolicitudRepository iSolicitudRepository;

    private IPublicacionRepository iPublicacionRepository;


    @Override
    public List<SolicitudDTO> getAll(){

        List<Solicitud> solicitudList = this.iSolicitudRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        /*List<Solicitud> solicitudListResponse = new ArrayList<>();
        for (Solicitud solicitud: solicitudList) {
            if(solicitud.getEstado().equals(1L) || solicitud.getEstado().equals(2L)){
                solicitudListResponse.add(solicitud);
            }
        }*/
        return solicitudList.stream().map(SolicitudMapper.INSTANCE::toSolicitudDTO).collect(Collectors.toList());

    }


    @Override
    public SolicitudDTO getOne(Long id){
        Solicitud solicitud = this.iSolicitudRepository.findById(id).orElse(null);
        return SolicitudMapper.INSTANCE.toSolicitudDTO(solicitud);
    }

    @Override
    public Boolean checkUserSolicitud(Long userId, Long publicacionId){
        List<Solicitud> solicitudList = this.iSolicitudRepository.findAll();
        for (Solicitud solicitud: solicitudList) {
            if (solicitud.getIdUsuario().equals(userId) && solicitud.getIdPublicacion().equals(publicacionId)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    @Override
    public Boolean crearSolicitud(SolicitudDTO solicitudDTO){
        Solicitud solicitud = SolicitudMapper.INSTANCE.toSolicitud(solicitudDTO);
        this.iSolicitudRepository.save(solicitud);
        return Boolean.TRUE;
    }

    @Override
    public Boolean actualizarSolicitud(SolicitudDTO solicitudDTO){
        Solicitud solicitud = SolicitudMapper.INSTANCE.toSolicitud(solicitudDTO);
        this.iSolicitudRepository.save(solicitud);
        return Boolean.TRUE;
    }


    @Override
    public Boolean eliminarSolicitud(Long id){
        this.iSolicitudRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean aceptarSolicitud(Long idSolicitud){
        Solicitud solicitud = this.iSolicitudRepository.findById(idSolicitud).orElse(null);
        if (solicitud!=null){

            solicitud.setEstado(1L);
            this.iSolicitudRepository.save(solicitud);

            Publicacion publicacion = this.iPublicacionRepository.findById(solicitud.getIdPublicacion()).orElse(null);
            publicacion.setEstado(0L);
            this.iPublicacionRepository.save(publicacion);


            Long idPublicacion = solicitud.getIdPublicacion();
            List<Solicitud> solicitudesDescartadas = new ArrayList<>();

            List<Solicitud> solicitudList = this.iSolicitudRepository.findAll();

            for (Solicitud solicitud1: solicitudList) {
                if (solicitud1.getIdPublicacion().equals(idPublicacion) && !solicitud.equals(solicitud1)){
                    solicitud1.setEstado(3L);
                    this.iSolicitudRepository.save(solicitud1);
                    solicitudesDescartadas.add(solicitud1);
                }
            }
            System.out.println(solicitudesDescartadas);

            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }


    @Override
    public Boolean rechazarSolicitud(Long idSolicitud){
        Solicitud solicitud = this.iSolicitudRepository.findById(idSolicitud).orElse(null);

        if (solicitud != null){
            solicitud.setEstado(0L);
            this.iSolicitudRepository.save(solicitud);
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }

    }




    @Autowired
    public void setiSolicitudRepository(ISolicitudRepository iSolicitudRepository) {
        this.iSolicitudRepository = iSolicitudRepository;
    }

    @Autowired
    public void setiPublicacionRepository(IPublicacionRepository iPublicacionRepository) {
        this.iPublicacionRepository = iPublicacionRepository;
    }

}
