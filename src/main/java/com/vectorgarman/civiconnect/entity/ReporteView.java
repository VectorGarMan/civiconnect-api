package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "vista_reportes_completos")
public class ReporteView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idreporte;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechacreacion = LocalDate.now();

    @Column(nullable = false)
    private LocalDate fechaactualizacion = LocalDate.now();

    @Column(nullable = false)
    private Long idusuariocreador;

    @Column(nullable = false)
    private String creador;

    @Column(nullable = false)
    private String emailcreador;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String estadoreporte;

    @Column(nullable = false)
    private String colorestado;

    @Column(nullable = false)
    private String prioridad;

    @Column(nullable = false)
    private int nivelnumerico;

    @Column(nullable = false)
    private String colonia;

    @Column(nullable = false)
    private String municipio;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String referencia;

    @Column(nullable = false)
    private Long totalvotos;

    @Column(nullable = false)
    private Long totalcomentarios;
}
