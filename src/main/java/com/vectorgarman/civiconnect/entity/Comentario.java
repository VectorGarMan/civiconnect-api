package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
    private LocalDateTime fechacreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaactualizacion = LocalDateTime.now();

    @Column(nullable = true)
    private Boolean editado;

    @Column(nullable = true)
    private Boolean esoficial;
}
