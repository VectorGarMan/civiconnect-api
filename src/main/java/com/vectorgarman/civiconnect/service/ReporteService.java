package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.dto.ReporteDto;
import com.vectorgarman.civiconnect.dto.ReporteViewDto;
import com.vectorgarman.civiconnect.entity.*;
import com.vectorgarman.civiconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

//    public ResponseEntity<ApiResponse<ReporteDto>> crear(Reporte reporte, List<MultipartFile> evidencias) {
//        try {
//            Reporte reporteGuardado = reporteRepository.save(reporte);
//            ReporteDto responseDto = new ReporteDto();
//            List<Evidencia> evidenciaList = new ArrayList<>();
//
//            if (evidencias != null && !evidencias.isEmpty()) {
//
//                for (MultipartFile file : evidencias) {
//                    Evidencia evidencia = new Evidencia();
//                    evidencia.setIdreporte(reporteGuardado.getIdreporte());
//                    evidencia.setTipoarchivo(file.getContentType());
//                    evidencia.setArchivo(file.getBytes());
//                    Evidencia evidenciaResponse = evidenciaRepository.save(evidencia);
//                    evidenciaList.add(evidenciaResponse);
//                }
//            }
//
//            responseDto.setReporte(reporteGuardado);
//            responseDto.setEvidencias(evidenciaList);
//
//            ApiResponse<ReporteDto> res = new ApiResponse<>(
//                    "OK",
//                    "Reporte creado correctamente.",
//                    null,
//                    responseDto
//            );
//
//            return ResponseEntity.status(HttpStatus.OK).body(res);
//
//        } catch (Exception e) {
//
//            ApiResponse<ReporteDto> res = new ApiResponse<>(
//                    "ERROR",
//                    "No se pudo crear el reporte",
//                    e.getMessage(),
//                    null
//            );
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
//        }
//    }

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



}
