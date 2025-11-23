package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcomentario;

    @Column(nullable = false)
    private Long idusuario;

    @Column(nullable = false)
    private Long idreporte;

    @Column(nullable = true)
    private Long idcomentariopadre;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private LocalDate fechacreacion = LocalDate.now();

    @Column(nullable = false)
    private LocalDate fechaactualizacion = LocalDate.now();

    @Column(nullable = false)
    private Boolean editado;

    @Column(nullable = false)
    private Boolean esoficial;
}
