package com.vectorgarman.civiconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Usuario {
    private Long idusuario;
    private int idtipousuario;
    private int idcolonia;
    private String nombreusuario;
    private LocalDate fecharegistro = LocalDate.now();
    private Boolean empleadogubverificado;
    private String email;
    private String contrasena;
}
