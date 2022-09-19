package com.uis.lovpets.service.impl;

import com.uis.lovpets.dto.TipoMascotaDTO;
import com.uis.lovpets.mapper.TipoMascotaMapper;
import com.uis.lovpets.model.TipoMascota;
import com.uis.lovpets.repository.ITipoMascotaRepository;
import com.uis.lovpets.service.interfaces.ITipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoMascotaService implements ITipoMascotaService {

    private ITipoMascotaRepository iTipoMascotaRepository;

    @Override
    public List<TipoMascotaDTO> getAll(){
        List<TipoMascota> tipoMascotaList = this.iTipoMascotaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return tipoMascotaList.stream().map(TipoMascotaMapper.INSTANCE::toTipoMascotaDTO).collect(Collectors.toList());
    }


    @Autowired
    public void setiTipoMascotaRepository(ITipoMascotaRepository iTipoMascotaRepository) {
        this.iTipoMascotaRepository = iTipoMascotaRepository;
    }

}
