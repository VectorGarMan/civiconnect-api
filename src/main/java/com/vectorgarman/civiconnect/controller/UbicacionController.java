package com.vectorgarman.civiconnect.controller;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.entity.Colonia;
import com.vectorgarman.civiconnect.entity.Estado;
import com.vectorgarman.civiconnect.entity.Municipio;
import com.vectorgarman.civiconnect.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    @Autowired
    private UbicacionService service;

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
