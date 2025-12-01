package com.vectorgarman.civiconnect.dto;

import lombok.Data;

@Data
public class ActualizarNombreUsuarioRequest {
    private Long idusuario;
    private String nuevoNombreUsuario;
    private Long idcolonia;
}
