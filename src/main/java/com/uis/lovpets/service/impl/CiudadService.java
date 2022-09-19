package com.uis.lovpets.service.impl;

import com.uis.lovpets.dto.CiudadDTO;
import com.uis.lovpets.mapper.CiudadMapper;
import com.uis.lovpets.model.Ciudad;
import com.uis.lovpets.repository.ICiudadRepository;
import com.uis.lovpets.service.interfaces.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CiudadService implements ICiudadService {

    private ICiudadRepository iCiudadRepository;

    @Override
    public List<CiudadDTO> getAll(){
        List<Ciudad> ciudadList= this.iCiudadRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return ciudadList.stream().map(CiudadMapper.INSTANCE::toCiudadDTO).collect(Collectors.toList());
    }


    @Autowired
    public void setiCiudadRepository(ICiudadRepository iCiudadRepository) {
        this.iCiudadRepository = iCiudadRepository;
    }

}
