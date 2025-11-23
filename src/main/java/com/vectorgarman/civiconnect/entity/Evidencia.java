package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "evidencia")
public class Evidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idevidencia;

    @Column(nullable = false)
    private Long idreporte;

    @Column(nullable = false)
    private String tipoarchivo;

    @Column(nullable = false)
    private byte[] archivo;
}
