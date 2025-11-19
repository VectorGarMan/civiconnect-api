package com.vectorgarman.civiconnect.controller;

// Estos van a ser mis endpoints.

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.dto.LoginRequest;
import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.dto.UsuarioDto;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Optional<UsuarioDto>>> login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crear(usuario);
    }

    @GetMapping("/obtener")
    public List<Usuario> list(){
        return service.findAll();
    }

    @GetMapping("/obtener/{idusuario}")
    public Usuario obtener(@PathVariable Long idusuario) {
        return service.getById(idusuario);
    }

    @GetMapping("/ubicacion/{idusuario}")
    public ResponseEntity<ApiResponse<Ubicacion>> obtenerUbicacion(@PathVariable Long idusuario) {
        return service.obtenerUbicacion(idusuario);
    }
}
