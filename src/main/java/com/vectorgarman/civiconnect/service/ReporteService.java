package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.*;
import com.vectorgarman.civiconnect.entity.*;
import com.vectorgarman.civiconnect.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private NivelPrioridadRepository nivelPrioridadRepository;

    @Autowired
    private EstadoReporteRepository estadoReporteRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ReporteViewRepository reporteViewRepository;

    @Autowired
    private UsuarioVotaReporteRepository usuarioVotaReporteRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EstadisticasRepository estadisticasRepository;

    public ResponseEntity<ApiResponse<List<Estadisticas>>> findAllEstadisticas() {
        Estadisticas estadisticas = estadisticasRepository.findAll();

        if (estadisticasRepository.isEmpty()) {
            ApiResponse<List<NivelPrioridad>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron estadisticas.",
                    "La base de datos no tiene registros de estadisticas de los reportes.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<NivelPrioridad>> res = new ApiResponse<>(
                "OK",
                "Niveles de prioridad obtenidos correctamente.",
                null,
                listNivelPrioridades
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<NivelPrioridad>>> findAllNivelPrioridad() {
        List<NivelPrioridad> listNivelPrioridades = nivelPrioridadRepository.findAll();

        if (listNivelPrioridades.isEmpty()) {
            ApiResponse<List<NivelPrioridad>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron niveles de prioridad.",
                    "La base de datos no tiene registros de niveles de prioridad.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<NivelPrioridad>> res = new ApiResponse<>(
                "OK",
                "Niveles de prioridad obtenidos correctamente.",
                null,
                listNivelPrioridades
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<EstadoReporte>>> findAllEstadosReporte() {
        List<EstadoReporte> listEstadosReporte = estadoReporteRepository.findAll();

        if (listEstadosReporte.isEmpty()) {
            ApiResponse<List<EstadoReporte>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron estados de reporte.",
                    "La base de datos no tiene registros de estados de reporte.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<EstadoReporte>> res = new ApiResponse<>(
                "OK",
                "Estados de reporte obtenidos correctamente.",
                null,
                listEstadosReporte
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Categoria>>> findAllCategorias() {
        List<Categoria> listCategorias = categoriaRepository.findAll();

        if (listCategorias.isEmpty()) {
            ApiResponse<List<Categoria>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron categorias de reporte.",
                    "La base de datos no tiene registros de categorias de reporte.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Categoria>> res = new ApiResponse<>(
                "OK",
                "Categorias de reporte obtenidas correctamente.",
                null,
                listCategorias
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Evidencia>>> findEvidenciasByReporte(Long idreporte) {
        List<Evidencia> listEvidencias = evidenciaRepository.findByIdreporte(idreporte);

        if (listEvidencias.isEmpty()) {
            ApiResponse<List<Evidencia>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron evidencias.",
                    "La base de datos no tiene registros de evidencias de ese reporte.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Evidencia>> res = new ApiResponse<>(
                "OK",
                "Evidencias de ese reporte obtenidas correctamente.",
                null,
                listEvidencias
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Comentario>>> findComentariosByReporte(Long idreporte) {
        List<Comentario> listComentarios = comentarioRepository.findByIdreporte(idreporte);

        if (listComentarios.isEmpty()) {
            ApiResponse<List<Comentario>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron comentarios.",
                    "La base de datos no tiene registros de comentarios de ese reporte.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Comentario>> res = new ApiResponse<>(
                "OK",
                "Comentarios de ese reporte obtenidos correctamente.",
                null,
                listComentarios
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Comentario>>> findComentariosByUsuario(Long idusuario) {
        List<Comentario> listComentarios = comentarioRepository.findByIdusuario(idusuario);

        if (listComentarios.isEmpty()) {
            ApiResponse<List<Comentario>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron comentarios.",
                    "La base de datos no tiene registros de comentarios de ese usuario.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Comentario>> res = new ApiResponse<>(
                "OK",
                "Comentarios de ese usuario obtenidos correctamente.",
                null,
                listComentarios
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Comentario>>> findComentariosByComentarioPadre(Long idcomentariopadre) {
        List<Comentario> listComentarios = comentarioRepository.findByIdcomentariopadre(idcomentariopadre);

        if (listComentarios.isEmpty()) {
            ApiResponse<List<Comentario>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron comentarios.",
                    "La base de datos no tiene registros de comentarios de ese comentario padre.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Comentario>> res = new ApiResponse<>(
                "OK",
                "Comentarios del comentario padre obtenidos correctamente.",
                null,
                listComentarios
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> findAllReportes() {

        List<ReporteView> listReportesView = reporteViewRepository.findAll();

        if (listReportesView.isEmpty()) {
            ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron reportes.",
                    "La base de datos no tiene registros.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        List<ReporteViewDto> listReporteDto = listReportesView.stream()
                .map(reporteView -> {

                    List<Comentario> listComentarios =
                            comentarioRepository.findByIdreporte(reporteView.getIdreporte());

                    List<Evidencia> listEvidencias =
                            evidenciaRepository.findByIdreporte(reporteView.getIdreporte());

                    ReporteViewDto dto = new ReporteViewDto();
                    dto.setReporteView(reporteView);
                    dto.setComentarios(listComentarios);
                    dto.setEvidencias(listEvidencias);

                    return dto;
                })
                .toList();

        ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                "OK",
                "Reportes obtenidos correctamente.",
                null,
                listReporteDto
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> findAllReportesPorVotoUsuario(Long idusuario) {
        List<UsuarioVotaReporte> votos = usuarioVotaReporteRepository.findByIdIdusuario(idusuario);

        if (votos.isEmpty()) {
            ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron votos.",
                    "La base de datos no tiene registros de votos de ese usuario.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        List<Long> idsReportesVotados = votos.stream()
                .map(v -> v.getId().getIdreporte())
                .toList();

        List<ReporteView> listReportesView = reporteViewRepository.findByIdreporteIn(idsReportesVotados);

        List<ReporteViewDto> listReporteDto = listReportesView.stream()
                .map(reporteView -> {

                    List<Comentario> listComentarios =
                            comentarioRepository.findByIdreporte(reporteView.getIdreporte());

                    List<Evidencia> listEvidencias =
                            evidenciaRepository.findByIdreporte(reporteView.getIdreporte());

                    ReporteViewDto dto = new ReporteViewDto();
                    dto.setReporteView(reporteView);
                    dto.setComentarios(listComentarios);
                    dto.setEvidencias(listEvidencias);

                    return dto;
                })
                .toList();

        ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                "OK",
                "Reportes obtenidos correctamente.",
                null,
                listReporteDto
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<ReporteViewDto>>> findAllReportesPorIdUsuario(Long idusuario) {
        List<ReporteView> listReportesView = reporteViewRepository.findByIdusuariocreador(idusuario);

        if (listReportesView.isEmpty()) {
            ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron reportes.",
                    "La base de datos no tiene registros de reportes de ese usuario.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        List<ReporteViewDto> listReporteDto = listReportesView.stream()
                .map(reporteView -> {

                    List<Comentario> listComentarios =
                            comentarioRepository.findByIdreporte(reporteView.getIdreporte());

                    List<Evidencia> listEvidencias =
                            evidenciaRepository.findByIdreporte(reporteView.getIdreporte());

                    ReporteViewDto dto = new ReporteViewDto();
                    dto.setReporteView(reporteView);
                    dto.setComentarios(listComentarios);
                    dto.setEvidencias(listEvidencias);

                    return dto;
                })
                .toList();

        ApiResponse<List<ReporteViewDto>> res = new ApiResponse<>(
                "OK",
                "Reportes obtenidos correctamente.",
                null,
                listReporteDto
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<ReporteViewDto>> findReporteById(Long idreporte) {
        ReporteView reporteView = reporteViewRepository.findById(idreporte).orElse(null);

        if (reporteView == null) {
            ApiResponse<ReporteViewDto> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontró el reporte.",
                    "La base de datos no tiene un reporte con ese ID.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        List<Comentario> listComentarios = comentarioRepository.findByIdreporte(idreporte);
        List<Evidencia> listEvidencias = evidenciaRepository.findByIdreporte(idreporte);

        ReporteViewDto dto = new ReporteViewDto();
        dto.setReporteView(reporteView);
        dto.setComentarios(listComentarios);
        dto.setEvidencias(listEvidencias);

        ApiResponse<ReporteViewDto> res = new ApiResponse<>(
                "OK",
                "Reporte obtenido correctamente.",
                null,
                dto
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Transactional
    public ResponseEntity<ApiResponse<ReporteDto>> crearActualizar(
            Reporte reporte,
            List<MultipartFile> evidenciasNuevas,
            List<Long> evidenciasIdsEliminar
    ) {
        try {

            boolean esActualizacion = reporte.getIdreporte() != null;

            Reporte reporteGuardado = reporteRepository.save(reporte);

            if (esActualizacion && evidenciasIdsEliminar != null && !evidenciasIdsEliminar.isEmpty()) {
                evidenciaRepository.deleteAllById(evidenciasIdsEliminar);
            }

            if (evidenciasNuevas != null && !evidenciasNuevas.isEmpty()) {
                for (MultipartFile file : evidenciasNuevas) {
                    Evidencia evidencia = new Evidencia();
                    evidencia.setIdreporte(reporteGuardado.getIdreporte());
                    evidencia.setTipoarchivo(file.getContentType());
                    evidencia.setArchivo(file.getBytes());

                    evidenciaRepository.save(evidencia);
                }
            }

            List<Evidencia> evidenciasFinales =
                    evidenciaRepository.findByIdreporte(reporteGuardado.getIdreporte());

            ReporteDto responseDto = new ReporteDto();
            responseDto.setReporte(reporteGuardado);
            responseDto.setEvidencias(evidenciasFinales);

            ApiResponse<ReporteDto> res = new ApiResponse<>(
                    "OK",
                    esActualizacion ? "Reporte actualizado correctamente." : "Reporte creado correctamente.",
                    null,
                    responseDto
            );

            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception e) {
            ApiResponse<ReporteDto> res = new ApiResponse<>(
                    "ERROR",
                    "Error al crear/actualizar el reporte",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @Transactional
    public ResponseEntity<ApiResponse<Comentario>> crearActualizarComentario(Comentario comentario) {
        try {
            Comentario comentarioResponse = comentarioRepository.save(comentario);

            entityManager.flush();
            entityManager.refresh(comentarioResponse);

            ApiResponse<Comentario> res = new ApiResponse<>(
                    "OK",
                    "Comentario registrado correctamente.",
                    null,
                    comentarioResponse
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        } catch (Exception e) {
            ApiResponse<Comentario> res = new ApiResponse<>(
                    "ERROR",
                    "Error al registrar el comentario.",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    public ResponseEntity<ApiResponse<UsuarioVotaReporte>> votar(UsuarioVotaReporteId votoId) {
        try {

            if (usuarioVotaReporteRepository.existsById(votoId)) {
                ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                        "ERROR",
                        "Voto duplicado.",
                        "Este usuario ya ha votado en este reporte.",
                        null
                );
                return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
            }

            UsuarioVotaReporte voto = new UsuarioVotaReporte();
            voto.setId(votoId);

            UsuarioVotaReporte votoGuardado = usuarioVotaReporteRepository.save(voto);

            ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                    "OK",
                    "Voto registrado correctamente.",
                    null,
                    votoGuardado
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        } catch (DataIntegrityViolationException e) {

            if (e.getMessage().contains("fk_voto_usuario") || e.getMessage().contains("idusuario")) {
                ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                        "ERROR",
                        "Usuario no encontrado.",
                        "El usuario especificado no existe.",
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            if (e.getMessage().contains("fk_voto_reporte") || e.getMessage().contains("idreporte")) {
                ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                        "ERROR",
                        "Reporte no encontrado.",
                        "El reporte especificado no existe.",
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            // Otros errores de integridad
            ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                    "ERROR",
                    "Error de integridad de datos.",
                    e.getMostSpecificCause().getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        } catch (Exception e) {
            ApiResponse<UsuarioVotaReporte> res = new ApiResponse<>(
                    "ERROR",
                    "Error al registrar el voto.",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    public ResponseEntity<ApiResponse<String>> eliminarVoto(UsuarioVotaReporteId votoId) {
        try {
            if (!usuarioVotaReporteRepository.existsById(votoId)) {
                ApiResponse<String> res = new ApiResponse<>(
                        "ERROR",
                        "Voto no encontrado.",
                        "No existe un voto con esos datos.",
                        null
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }

            usuarioVotaReporteRepository.deleteById(votoId);

            ApiResponse<String> res = new ApiResponse<>(
                    "OK",
                    "Voto eliminado correctamente.",
                    null,
                    "Voto removido"
            );

            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception e) {
            ApiResponse<String> res = new ApiResponse<>(
                    "ERROR",
                    "Error al eliminar el voto.",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    public ResponseEntity<ApiResponse<String>> eliminarComentario(EliminarComentarioRequest request) {
        try {
            int filasEliminadas = comentarioRepository.deleteByIdAndUsuario(
                    request.getIdcomentario(),
                    request.getIdusuario()
            );

            if (filasEliminadas == 0) {
                ApiResponse<String> res = new ApiResponse<>(
                        "ERROR",
                        "No se pudo eliminar el comentario.",
                        "El comentario no existe o no pertenece a este usuario.",
                        null
                );
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
            }

            ApiResponse<String> res = new ApiResponse<>(
                    "OK",
                    "Comentario eliminado correctamente.",
                    null,
                    "Comentario removido"
            );

            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception e) {
            ApiResponse<String> res = new ApiResponse<>(
                    "ERROR",
                    "Error al eliminar el comentario.",
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    public ResponseEntity<ApiResponse<Reporte>> findReporteEntityById(Long idreporte) {
        Reporte reporte = reporteRepository.findById(idreporte).orElse(null);

        if (reporte == null) {
            ApiResponse<Reporte> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontró el reporte.",
                    "La base de datos no tiene un reporte con ese ID.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<Reporte> res = new ApiResponse<>(
                "OK",
                "Reporte obtenido correctamente.",
                null,
                reporte
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
