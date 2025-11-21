package com.vectorgarman.civiconnect.controller;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.entity.Categoria;
import com.vectorgarman.civiconnect.entity.EstadoReporte;
import com.vectorgarman.civiconnect.entity.NivelPrioridad;
import com.vectorgarman.civiconnect.entity.TipoArchivo;
import com.vectorgarman.civiconnect.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
    @Autowired
    private ReporteService service;

    @GetMapping("/nivelPrioridad/obtener")
    public ResponseEntity<ApiResponse<List<NivelPrioridad>>> listNivelPrioridad(){
        return service.findAllNivelPrioridad();
    }

    @GetMapping("/estadoReporte/obtener")
    public ResponseEntity<ApiResponse<List<EstadoReporte>>> listEstadoReporte(){
        return service.findAllEstadosReporte();
    }

    @GetMapping("/categoria/obtener")
    public ResponseEntity<ApiResponse<List<Categoria>>> listCategoria(){
        return service.findAllCategorias();
    }

    @GetMapping("/tipoArchivo/obtener")
    public ResponseEntity<ApiResponse<List<TipoArchivo>>> listTipoArchivo(){
        return service.findAllTiposArchivo();
    }
}
