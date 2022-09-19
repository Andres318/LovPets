package com.uis.lovpets.service.impl;

import com.uis.lovpets.dto.PublicacionDTO;
import com.uis.lovpets.mapper.PublicacionMapper;
import com.uis.lovpets.model.Publicacion;
import com.uis.lovpets.repository.IPublicacionRepository;
import com.uis.lovpets.service.interfaces.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionService implements IPublicacionService {

    private IPublicacionRepository iPublicacionRepository;

    @Override
    public List<PublicacionDTO> getAll(){
        List<Publicacion> publicacionList= this.iPublicacionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return publicacionList.stream().map(PublicacionMapper.INSTANCE::toPublicacionDTO).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO getOne(Long id){
        Publicacion publicacion = this.iPublicacionRepository.findById(id).orElse(null);
        return PublicacionMapper.INSTANCE.toPublicacionDTO(publicacion);
    }

    @Override
    public Boolean crearPublicacion(PublicacionDTO publicacionDTO){

        Publicacion publicacion = PublicacionMapper.INSTANCE.toPublicacion(publicacionDTO);
        this.iPublicacionRepository.save(publicacion);

        return Boolean.TRUE;
    }

    @Override
    public Boolean actualizarPublicacion(PublicacionDTO publicacionDTO){
        Publicacion publicacion = PublicacionMapper.INSTANCE.toPublicacion(publicacionDTO);
        this.iPublicacionRepository.save(publicacion);
        return Boolean.TRUE;
    }


    @Override
    public Boolean eliminarPublicacion(Long id){
        this.iPublicacionRepository.deleteById(id);
        return Boolean.TRUE;
    }



    @Autowired
    public void setiPublicacionRepository(IPublicacionRepository iPublicacionRepository) {
        this.iPublicacionRepository = iPublicacionRepository;
    }
}
