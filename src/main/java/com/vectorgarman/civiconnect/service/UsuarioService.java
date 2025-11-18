package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    public Usuario crear(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    public Ubicacion obtenerUbicacion(Long idusuario) {
        return usuarioRepository.obtenerUbicacionPorUsuario(idusuario);
    }
}
