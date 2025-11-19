package com.vectorgarman.civiconnect.dto;

import java.time.LocalDate;

public class UsuarioDto {
    private Long idusuario;
    private Long idtipousuario;
    private Long idcolonia;
    private String nombreusuario;
    private LocalDate fecharegistro = LocalDate.now();
    private Boolean empleadogubverificado;
    private String email;

    public UsuarioDto() {
    }

    public UsuarioDto(Long idusuario, Long idtipousuario, Long idcolonia, String nombreusuario, LocalDate fecharegistro, Boolean empleadogubverificado, String email) {
        this.idusuario = idusuario;
        this.idtipousuario = idtipousuario;
        this.idcolonia = idcolonia;
        this.nombreusuario = nombreusuario;
        this.fecharegistro = fecharegistro;
        this.empleadogubverificado = empleadogubverificado;
        this.email = email;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Long idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Long getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Long idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public LocalDate getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(LocalDate fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Boolean getEmpleadogubverificado() {
        return empleadogubverificado;
    }

    public void setEmpleadogubverificado(Boolean empleadogubverificado) {
        this.empleadogubverificado = empleadogubverificado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
