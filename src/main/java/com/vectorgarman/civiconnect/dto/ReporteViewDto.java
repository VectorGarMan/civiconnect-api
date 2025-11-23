package com.vectorgarman.civiconnect.dto;

import com.vectorgarman.civiconnect.entity.Comentario;
import com.vectorgarman.civiconnect.entity.Evidencia;
import com.vectorgarman.civiconnect.entity.ReporteView;
import lombok.Data;

import java.util.List;

@Data
public class ReporteViewDto {
    ReporteView reporteView;
    List<Comentario> comentarios;
    List<Evidencia> evidencias;
}
