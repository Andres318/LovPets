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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 2619333253149501933L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "id_ciudad")
    private Long idCiudad;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", insertable = false, updatable = false)
    private Ciudad ciudad;
}

          /*id: number;
          nombre: string;
          direccion: string;
          idCiudad: number;
          fk --> nombreCiudad: string;
          telefono: string;
          correo: string;
          contrasena: string;
          Validacion Front --> confirmarContrasena: string;*/