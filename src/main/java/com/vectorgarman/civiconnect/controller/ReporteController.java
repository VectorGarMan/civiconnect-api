package com.vectorgarman.civiconnect.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vectorgarman.civiconnect.dto.*;
import com.vectorgarman.civiconnect.entity.*;
import com.vectorgarman.civiconnect.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

    @GetMapping("/obtenerPorId/{idreporte}")
    public ResponseEntity<ApiResponse<ReporteViewDto>> getReporteById(@PathVariable Long idreporte){
        return service.findReporteById(idreporte);
    }

    @PostMapping("/comentario/crearActualizar")
    public ResponseEntity<ApiResponse<Comentario>> comentar(@RequestBody Comentario comentario) {
        return service.crearActualizarComentario(comentario);
    }

    @PostMapping("/votar")
    public ResponseEntity<ApiResponse<UsuarioVotaReporte>> votar(@RequestBody UsuarioVotaReporteId usuarioVotaReporteId) {
        return service.votar(usuarioVotaReporteId);
    }

    @DeleteMapping("/eliminarVoto")
    public ResponseEntity<ApiResponse<String>> eliminarVoto(@RequestBody UsuarioVotaReporteId usuarioVotaReporteId) {
        return service.eliminarVoto(usuarioVotaReporteId);
    }

    @DeleteMapping("/eliminarComentario")
    public ResponseEntity<ApiResponse<String>> eliminarComentario(@RequestBody EliminarComentarioRequest request) {
        return service.eliminarComentario(request);
    }

    /**
     * Endpoint para crear o actualizar un reporte junto con sus evidencias asociadas.
     *
     * Comportamiento:
     * 1. Si el objeto `reporte` incluye un `idreporte` existente:
     *    - Se actualiza el reporte correspondiente.
     *    - Se eliminan las evidencias cuyo ID se pase en `evidenciasIdsEliminar`.
     *    - Se agregan nuevas evidencias enviadas en `evidencia`.
     *    - Se devuelve la lista completa de evidencias actualizadas.
     * 2. Si el objeto `reporte` no incluye `idreporte` (es null):
     *    - Se crea un nuevo reporte.
     *    - Se agregan las evidencias enviadas en `evidencia`.
     *    - Se devuelve la lista de evidencias del nuevo reporte.
     *
     * Parámetros del request (multipart/form-data):
     * - "reporte" (String, JSON): Contiene los datos del reporte a crear o actualizar.
     * - "evidencia" (MultipartFile, opcional): Archivos que se desean agregar al reporte.
     * - "evidenciasIdsEliminar" (String, opcional, JSON array de Longs): IDs de las evidencias que se desean eliminar.
     *
     * Ejemplo de uso en Postman:
     * - reporte: {"idreporte":15, ... Agregar los demás datos del reporte}
     * - evidencia: archivo1.png
     * - evidencia: archivo2.jpg
     * - evidenciasIdsEliminar: [3,7]
     *
     * Respuesta:
     * - Objeto ApiResponse<ReporteDto> que contiene:
     *   - reporte: el reporte creado o actualizado
     *   - evidencias: lista completa de evidencias asociadas después de la operación
     *
     * Notas:
     * - El endpoint maneja transacciones; si ocurre algún error, se realiza rollback automático.
     * - Las evidencias que no se incluyan en `evidenciasIdsEliminar` y que ya existan, permanecerán intactas.
     */
    @PostMapping(
            value = "/crearActualizar",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ResponseEntity<ApiResponse<ReporteDto>> crearActualizar(
            @RequestPart(value = "reporte") String reporteJson,
            @RequestPart(value = "evidencia", required = false) List<MultipartFile> evidencia,
            @RequestPart(value = "evidenciasIdsEliminar", required = false) String evidenciasIdsEliminarJson
    ) {
        ObjectMapper mapper = new ObjectMapper();
        Reporte reporte;
        List<Long> evidenciasIdsEliminar = new ArrayList<>();

        try {
            // Convertimos el reporte JSON → objeto
            reporte = mapper.readValue(reporteJson, Reporte.class);

            // Convertimos los IDs a eliminar (solo si vienen)
            if (evidenciasIdsEliminarJson != null && !evidenciasIdsEliminarJson.isEmpty()) {
                evidenciasIdsEliminar = mapper.readValue(
                        evidenciasIdsEliminarJson,
                        new TypeReference<List<Long>>() {}
                );
            }
        } catch (Exception e) {
            ApiResponse<ReporteDto> res = new ApiResponse<>(
                    "ERROR",
                    "Error de integridad de datos.",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

        return service.crearActualizar(reporte, evidencia, evidenciasIdsEliminar);
    }

    @GetMapping("/obtenerVotadoPorUsuario/{idusuario}")
    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> listReportes(@PathVariable Long idusuario){
        return service.findAllReportesPorVotoUsuario(idusuario);
    }

    @GetMapping("/obtenerPorIdUsuario/{idusuario}")
    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> listReportesPorIdUsuario(@PathVariable Long idusuario){
        return service.findAllReportesPorIdUsuario(idusuario);
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
