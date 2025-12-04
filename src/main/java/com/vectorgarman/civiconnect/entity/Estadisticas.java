package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vista_estadisticas_categoria")
public class Estadisticas {

    @Id
    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Long totalreportes;

    @Column(nullable = false)
    private Long pendientes;

    @Column(nullable = true)
    private Long enproceso;

    @Column(nullable = false)
    private Long solucionados;
}
