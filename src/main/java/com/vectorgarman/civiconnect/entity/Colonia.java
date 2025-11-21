package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "colonia")
public class Colonia {
    @Id
    private Long idcolonia;

    @Column(nullable = false)
    private Long idmunicipio;

    @Column(nullable = false)
    private Long idestado;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String codigopostal;
}
