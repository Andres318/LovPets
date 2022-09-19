package com.uis.lovpets.controller;

import com.uis.lovpets.dto.TipoMascotaDTO;
import com.uis.lovpets.service.interfaces.ITipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipoMascota")
public class TipoMascotaController {

    private ITipoMascotaService iTipoMascotaService;

    @GetMapping("/all")
    public ResponseEntity<List<TipoMascotaDTO>> getAll(){

        List<TipoMascotaDTO> tipoMascotaDTOS = this.iTipoMascotaService.getAll();
        return new ResponseEntity<>(tipoMascotaDTOS, HttpStatus.OK);

    }



    @Autowired
    public void setiTipoMascotaService(ITipoMascotaService iTipoMascotaService) {
        this.iTipoMascotaService = iTipoMascotaService;
    }
}
