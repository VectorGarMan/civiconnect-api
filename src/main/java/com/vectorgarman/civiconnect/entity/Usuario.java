package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @Column(nullable = false)
    private int idtipousuario;

    @Column(nullable = false)
    private int idcolonia;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String nombreusuario;

    @Column(nullable = false)
    private LocalDate fecharegistro = LocalDate.now();

    @Column(nullable = true)
    private Boolean empleadogubverificado;
}
