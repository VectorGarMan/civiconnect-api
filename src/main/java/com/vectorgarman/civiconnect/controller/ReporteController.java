package com.vectorgarman.civiconnect.controller;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.dto.ReporteDto;
import com.vectorgarman.civiconnect.dto.ReporteViewDto;
import com.vectorgarman.civiconnect.entity.*;
import com.vectorgarman.civiconnect.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {
    @Autowired
    private ReporteService service;

    @GetMapping("/obtener")
    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> listReportes(){
        return service.findAllReportes();
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<ReporteDto>> crear(@RequestBody ReporteDto reporteDto) {
        return service.crear(reporteDto);
    }

    @GetMapping("/obtenerVotadoPorUsuario/{idusuario}")
    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> listReportes(@PathVariable Long idusuario){
        return service.findAllReportesPorVotoUsuario(idusuario);
    }

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

    @GetMapping("/evidencia/obtenerPorReporte/{idreporte}")
    public ResponseEntity<ApiResponse<List<Evidencia>>> listEvidencia(@PathVariable Long idreporte){
        return service.findEvidenciasByReporte(idreporte);
    }

    @GetMapping("/comentario/obtenerPorReporte/{idreporte}")
    public ResponseEntity<ApiResponse<List<Comentario>>> listComentariosPorReporte(@PathVariable Long idreporte){
        return service.findComentariosByReporte(idreporte);
    }

    @GetMapping("/comentario/obtenerPorUsuario/{idusuario}")
    public ResponseEntity<ApiResponse<List<Comentario>>> listComentariosPorUsuario(@PathVariable Long idusuario){
        return service.findComentariosByUsuario(idusuario);
    }

    @GetMapping("/comentario/obtenerPorComentarioPadre/{idcomentariopadre}")
    public ResponseEntity<ApiResponse<List<Comentario>>> listComentariosPorComentarioPadre(@PathVariable Long idcomentariopadre){
        return service.findComentariosByComentarioPadre(idcomentariopadre);
    }
}
