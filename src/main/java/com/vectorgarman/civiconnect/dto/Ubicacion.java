package com.vectorgarman.civiconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ubicacion {
    private Long IdColonia;
    private String NombreColonia;

    private Long IdMunicipio;
    private String NombreMunicipio;

    private Long IdEstado;
    private String NombreEstado;
}
