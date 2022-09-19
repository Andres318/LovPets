package com.uis.lovpets.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
public class PublicacionDTO implements Serializable {

    private static final long serialVersionUID = 2175248422543999703L;

    private Long id;

    private String nombre;

    private String imagenB64;

    private Long edad;

    private String unidadEdad;

    private String genero;

    private String raza;

    private String gustos;

    private String observaciones;

    private Long idTipoMascota;

    private Long idUsuario;

    //campos derivados de las foráneas

    private Long idCiudad;

    private String nombreUsuario;

    private String direccionUsuario;

    private String nombreCiudadUsuario;

    private String telefonoUsuario;

    private String correoUsuario;

    private String tipoMascota;

}


/*
export interface PublicacionAdopcionModel {
    //campos propios
    id: number;
    idUsuario: number;
    nombre: string;
    imagenB64: string;
    edad: number;
    unidadEdad: string;
    idTipoMascota: number;
    genero: string;
    raza: string;
    gustos: string;
    observaciones: string;

    //campos derivados de las foráneas
    nombreUsuario: string;
    direccionUsuario: string;
    nombreCiudadUsuario: string;
    telefonoUsuario: string;
    correoUsuario: string;*/
