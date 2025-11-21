package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.entity.*;
import com.vectorgarman.civiconnect.repository.CategoriaRepository;
import com.vectorgarman.civiconnect.repository.EstadoReporteRepository;
import com.vectorgarman.civiconnect.repository.NivelPrioridadRepository;
import com.vectorgarman.civiconnect.repository.TipoArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private TipoArchivoRepository tipoArchivoRepository;

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

    public ResponseEntity<ApiResponse<List<TipoArchivo>>> findAllTiposArchivo() {
        List<TipoArchivo> listTiposArchivo = tipoArchivoRepository.findAll();

        if (listTiposArchivo.isEmpty()) {
            ApiResponse<List<TipoArchivo>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron tipos de archivo.",
                    "La base de datos no tiene registros de tipos de archivo.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<TipoArchivo>> res = new ApiResponse<>(
                "OK",
                "Tipos de archivo obtenidos correctamente.",
                null,
                listTiposArchivo
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
