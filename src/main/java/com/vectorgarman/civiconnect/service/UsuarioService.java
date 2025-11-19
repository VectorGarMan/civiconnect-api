package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.dto.LoginRequest;
import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.dto.UsuarioDto;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<ApiResponse<Optional<UsuarioDto>>> login(LoginRequest loginRequest) {
        Optional<UsuarioDto> usuarioEncontrado =
                usuarioRepository.findByEmailAndContrasena(loginRequest.getEmail(), loginRequest.getContrasena());

        boolean verificacionGubernamental = false;

        if (usuarioEncontrado.isPresent()) {
            verificacionGubernamental = usuarioEncontrado.get().getIdtipousuario() == 2 &&
                    (usuarioEncontrado.get().getEmpleadogubverificado() == null ||
                    !usuarioEncontrado.get().getEmpleadogubverificado());
        }

        if (usuarioEncontrado.isEmpty()) {
            ApiResponse<Optional<UsuarioDto>> res = new ApiResponse<>(
                    "ERROR",
                    "Credenciales incorrectas",
                    "Usuario no encontrado",
                    null
            );
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else if (verificacionGubernamental) {
            ApiResponse<Optional<UsuarioDto>> res = new ApiResponse<>(
                    "WARNING",
                    "Usuario gubernamental no verificado",
                    "Verificación pendiente",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        } else {
            ApiResponse<Optional<UsuarioDto>> res = new ApiResponse<>(
                    "OK",
                    "Inicio de sesión exitoso",
                    null,
                    usuarioEncontrado
            );
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }
}
