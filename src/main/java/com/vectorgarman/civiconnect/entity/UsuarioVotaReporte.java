package com.vectorgarman.civiconnect.entity;

import com.vectorgarman.civiconnect.dto.UsuarioVotaReporteId;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario_vota_reporte")
public class UsuarioVotaReporte {
    @EmbeddedId
    private UsuarioVotaReporteId id;

    @Column(nullable = false)
    private LocalDate fechavoto = LocalDate.now();
}
