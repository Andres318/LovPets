package com.uis.lovpets.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
public class SolicitudDTO implements Serializable {

    private static final long serialVersionUID = 7246507998295969107L;

    private Long id;

    private Boolean otrasMascotas;

    private String mascotas;

    private String condiciones;

    private Date fechaRegistro;

    private Long estado;

    private Long idPublicacion;

    private Long idUsuario;

    //Campos que provienen de foráneas

    private Long idCiudad;

    private String nombreUsuario;

    private String direccionUsuario;

    private String nombreCiudadUsuario;

    private String telefonoUsuario;

    private String correoUsuario;

    private String nombreMascota;

    private String nombreUsuarioPublicacion;

    private String telefonoUsuarioPublicacion;

    private String correoUsuarioPublicacion;

    private String imagenB64;

}




/*
export interface SolicitudAdopcionModel {
    //Campos propios
    id: number;
    idUsuario: number;
    idPublicacion: number;
    otrasMascotas: boolean;
    mascotas: string;
    condiciones: string;
    fechaRegistro: Date;
    estado: number;
    //Campos que provienen de foráneas
    nombreUsuario: string;
    direccionUsuario: string;
    nombreCiudadUsuario: string;
    telefonoUsuario: string;
    correoUsuario: string;
    nombreMascota: string;
    nombreUsuarioPublicacion: string;
    telefonoUsuarioPublicacion: string;
    correoUsuarioPublicacion: string;
    imagenB64: string;
}*/
