package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @Column(nullable = false)
    private Long idtipousuario;

    @Column(nullable = false)
    private Long idcolonia;

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
