package com.uis.lovpets.controller;

import com.uis.lovpets.dto.CiudadDTO;
import com.uis.lovpets.service.interfaces.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    private ICiudadService iCiudadService;


    @GetMapping("/all")
    public ResponseEntity<List<CiudadDTO>> findAllCities(){

        List<CiudadDTO> ciudadDTOList = this.iCiudadService.getAll();
        return new ResponseEntity<>(ciudadDTOList, HttpStatus.OK);

    }


    @Autowired
    public void setiCiudadService(ICiudadService iCiudadService) {
        this.iCiudadService = iCiudadService;
    }

}
