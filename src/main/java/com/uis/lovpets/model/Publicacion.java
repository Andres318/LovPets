package com.uis.lovpets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "publicacion")
public class Publicacion implements Serializable {

    private static final long serialVersionUID = -6628877207112272922L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "imagenB64")
    private String imagenB64;

    @Column(name = "edad")
    private Long edad;

    @Column(name = "unidad_edad")
    private String unidadEdad;

    @Column(name = "genero")
    private String genero;

    @Column(name = "raza")
    private String raza;

    @Column(name = "gustos")
    private String gustos;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "id_tipo_mascota")
    private Long idTipoMascota;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_mascota", insertable = false, updatable = false)
    private TipoMascota tipoMascotica;

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
