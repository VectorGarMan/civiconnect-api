package com.vectorgarman.civiconnect.controller;

// Estos van a ser mis endpoints.

import com.vectorgarman.civiconnect.dto.*;
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

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Optional<UsuarioDto>>> login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<Usuario>> crear(@RequestBody Usuario usuario) {
        return service.crear(usuario);
    }

    @GetMapping("/obtener")
    public ResponseEntity<ApiResponse<List<Usuario>>> list(){
        return service.findAll();
    }

    @GetMapping("/obtener/{idusuario}")
    public ResponseEntity<ApiResponse<Usuario>> obtener(@PathVariable Long idusuario) {
        return service.getById(idusuario);
    }

    @GetMapping("/ubicacion/{idusuario}")
    public ResponseEntity<ApiResponse<Ubicacion>> obtenerUbicacion(@PathVariable Long idusuario) {
        return service.obtenerUbicacion(idusuario);
    }

    @GetMapping("/verificarGubernamental")
    public ResponseEntity<String> verificarGubernamental(@RequestParam String email, String token) {
        return service.verificarGubernamentalValidToken(email, token);
    }

    @GetMapping("/recuperarContrasena")
    public ResponseEntity<ApiResponse<Optional<Usuario>>> recuperarContrasena(@RequestParam String email) {
        return service.verificarCambioContrasena(email);
    }

    @PostMapping("/cambiarContrasena")
    public ResponseEntity<ApiResponse<?>> cambiarContrasena(@RequestBody CambioContrasenaRequest cambioContrasenaRequest) {
        return service.cambiarContrasena(cambioContrasenaRequest);
    }
}
