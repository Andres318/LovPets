package com.uis.lovpets.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CiudadDTO implements Serializable {

    private static final long serialVersionUID = 1194773724605235898L;

    private Long id;

    private String nombre;

}
