package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "colonia")
public class Colonia {
    @Id
    private Long idcolonia;
    private Long idmunicipio;
    private Long idestado;
    private String nombre;
    private String codigopostal;
}
