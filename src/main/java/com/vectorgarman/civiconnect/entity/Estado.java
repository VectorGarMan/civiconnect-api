package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    private Long idestado;
    private String nombre;
    private String codigo;
}
