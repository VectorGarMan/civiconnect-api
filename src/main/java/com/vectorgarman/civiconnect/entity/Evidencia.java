package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "evidencia")
public class Evidencia {
    @Id
    private Long idevidencia;

    @Column(nullable = false)
    private Long idreporte;

    @Column(nullable = false)
    private Long idtipoarchivo;

    @Column(nullable = false)
    private String nombrearchivo;

    @Column(nullable = false)
    private String rutaarchivo;

    @Column(nullable = false)
    private int ordenvisualizacion;

    @Column(nullable = false)
    private int tamaniobytes;
}
