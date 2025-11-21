package com.vectorgarman.civiconnect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_archivo")
public class TipoArchivo {
    @Id
    private Long idtipoarchivo;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false)
    private String mimetype;

    @Column(nullable = false)
    private Long tamaniomaxmb;
}
