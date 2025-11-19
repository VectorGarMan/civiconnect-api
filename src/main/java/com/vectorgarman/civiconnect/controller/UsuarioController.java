package com.vectorgarman.civiconnect.controller;

// Estos van a ser mis endpoints.

import com.vectorgarman.civiconnect.dto.LoginRequest;
import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) { return service; }

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
    public Ubicacion obtenerUbicacion(@PathVariable Long idusuario) {
        return service.obtenerUbicacion(idusuario);
    }
}
