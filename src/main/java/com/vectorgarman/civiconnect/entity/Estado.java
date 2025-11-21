package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estado")
public class Estado {
    @Id
    private Long idestado;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;
}
