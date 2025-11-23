package com.vectorgarman.civiconnect.controller;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.entity.Colonia;
import com.vectorgarman.civiconnect.entity.Estado;
import com.vectorgarman.civiconnect.entity.Municipio;
import com.vectorgarman.civiconnect.service.UbicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
@Tag(name = "Ubicación", description = "Endpoints para la gestión de ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService service;

    @Operation(
            summary = "Listar todos los estados",
            description = "Retorna una lista con todos los estados registrados en el sistema"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Estados obtenidos correctamente"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron estados en la base de datos"
            )
    })
    @GetMapping("/estado/obtener")
    public ResponseEntity<ApiResponse<List<Estado>>> listEstados(){
        return service.findAll();
    }

    @GetMapping("/municipio/obtenerPorEstado")
    public ResponseEntity<ApiResponse<List<Municipio>>> listMunicipios(@RequestParam Long idestado){
        return service.findMunicipiosByEstado(idestado);
    }

    @GetMapping("/colonia/obtenerPorMunicipio")
    public ResponseEntity<ApiResponse<List<Colonia>>> listColonias(@RequestParam Long idmunicipio){
        return service.findColoniasByMunicipio(idmunicipio);
    }
}
