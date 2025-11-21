package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "nivel_prioridad")
public class NivelPrioridad {
    @Id
    private Long idnivelprioridad;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int nivelnumerico;

    @Column(nullable = false)
    private String descripcion;
}
