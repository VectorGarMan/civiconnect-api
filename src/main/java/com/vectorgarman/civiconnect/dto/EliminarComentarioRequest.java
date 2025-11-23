package com.vectorgarman.civiconnect.dto;

import lombok.Data;

@Data
public class EliminarComentarioRequest {
    private Long idusuario;
    private Long idcomentario;
}
