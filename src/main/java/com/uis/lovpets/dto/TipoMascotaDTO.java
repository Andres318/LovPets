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
public class TipoMascotaDTO implements Serializable {

    private static final long serialVersionUID = -7544196995020390157L;

    private Long id;

    private String nombre;
}
