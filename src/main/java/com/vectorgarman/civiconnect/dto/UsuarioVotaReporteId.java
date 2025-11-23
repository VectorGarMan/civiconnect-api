package com.vectorgarman.civiconnect.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioVotaReporteId implements Serializable {
    private Long idusuario;
    private Long idreporte;
}
