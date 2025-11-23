package com.vectorgarman.civiconnect.dto;

import com.vectorgarman.civiconnect.entity.Comentario;
import com.vectorgarman.civiconnect.entity.Evidencia;
import com.vectorgarman.civiconnect.entity.Reporte;
import com.vectorgarman.civiconnect.entity.ReporteView;
import lombok.Data;

import java.util.List;

@Data
public class ReporteDto {
    Reporte reporte;
    List<Comentario> comentarios;
    List<Evidencia> evidencias;
}
