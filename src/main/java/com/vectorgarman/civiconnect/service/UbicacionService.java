package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.entity.Colonia;
import com.vectorgarman.civiconnect.entity.Estado;
import com.vectorgarman.civiconnect.entity.Municipio;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.repository.ColoniaRepository;
import com.vectorgarman.civiconnect.repository.EstadoRepository;
import com.vectorgarman.civiconnect.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private ColoniaRepository coloniaRepository;

    public ResponseEntity<ApiResponse<List<Estado>>> findAll() {
        List<Estado> listEstados = estadoRepository.findAll();

        if (listEstados.isEmpty()) {
            ApiResponse<List<Estado>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron estados.",
                    "La base de datos no tiene registros de estados.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Estado>> res = new ApiResponse<>(
                "OK",
                "Estados obtenidos correctamente.",
                null,
                listEstados
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Municipio>>> findMunicipiosByEstado(Long idestado) {
        List<Municipio> listMunicipio = municipioRepository.findByIdestado(idestado);

        if (listMunicipio.isEmpty()) {
            ApiResponse<List<Municipio>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron municipios.",
                    "La base de datos no tiene registros de municipios.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Municipio>> res = new ApiResponse<>(
                "OK",
                "Municipios obtenidos correctamente.",
                null,
                listMunicipio
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<List<Colonia>>> findColoniasByMunicipio(Long idmunicipio) {
        List<Colonia> listColonia = coloniaRepository.findByIdmunicipio(idmunicipio);

        if (listColonia.isEmpty()) {
            ApiResponse<List<Colonia>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron colonias.",
                    "La base de datos no tiene registros de colonias.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Colonia>> res = new ApiResponse<>(
                "OK",
                "Colonias obtenidas correctamente.",
                null,
                listColonia
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
