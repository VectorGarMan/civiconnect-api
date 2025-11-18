package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio {
    @Id
    private Long idmunicipio;
    private Long idestado;
    private String nombre;
    private String codigo;
}