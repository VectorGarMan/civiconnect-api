package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idreporte;

    @Column(nullable = false)
    private Long idusuario;

    @Column(nullable = false)
    private Long idcolonia;

    @Column(nullable = false)
    private Long idnivelprioridad;

    @Column(nullable = false)
    private Long idestadoreporte;

    @Column(nullable = false)
    private Long idcategoria;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechacreacion = LocalDate.now();

    @Column(nullable = false)
    private LocalDate fechaactualizacion = LocalDate.now();

    @Column(nullable = true)
    private String solucionpropuesta;

    @Column(nullable = true)
    private String calle;

    @Column(nullable = true)
    private String referencia;
}
