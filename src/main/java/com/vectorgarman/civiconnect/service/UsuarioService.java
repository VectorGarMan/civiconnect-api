package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.ApiResponse;
import com.vectorgarman.civiconnect.dto.LoginRequest;
import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.dto.UsuarioDto;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.repository.UsuarioRepository;
import com.vectorgarman.civiconnect.template.VerificarGubernamentalTemplateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private TokenService tokenService;

    @Value("${spring.mail.destinatarioVerifGub}")
    private String destinatarioVerifGub;

    private void verificarGubernamental (Usuario usuario) {
        String destinatario = this.destinatarioVerifGub;
        String asunto = "CiviConnect - Verificar Usuario Gubernamental";

        String token = tokenService.generarTokenVerificacion(usuario.getEmail());
        String urlVerificacion = "http://localhost:8080/api/usuarios/verificarGubernamental?email=" + usuario.getEmail() + "&token=" + token;

        String mensaje = VerificarGubernamentalTemplateEnum.VERIFICACION_GUBERNAMENTAL.build(
                "EMAIL", usuario.getEmail(),
                "URL_VERIFICACION", urlVerificacion
        );

        mailService.enviarCorreo(destinatario, asunto, mensaje);
    }

    public ResponseEntity<ApiResponse<Usuario>> crear(Usuario usuario) {
        try {
            Usuario usuarioResponse = usuarioRepository.save(usuario);

            ApiResponse<Usuario> res = new ApiResponse<>(
                    "OK",
                    "Usuario creado correctamente.",
                    null,
                    usuarioResponse
            );

            verificarGubernamental(usuarioResponse);

            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (DataIntegrityViolationException e) {

            // Detecta si el mensaje indica que fue por el email duplicado
            if (e.getMessage().contains("usuario_email_key")) {
                ApiResponse<Usuario> res = new ApiResponse<>(
                        "ERROR",
                        "El correo ya está registrado.",
                        "El email proporcionado ya existe en la base de datos.",
                        null
                );
                return ResponseEntity.status(HttpStatus.CONFLICT).body(res); // 409
            }

            // Otros errores de integridad
            ApiResponse<Usuario> res = new ApiResponse<>(
                    "ERROR",
                    "Error de integridad de datos.",
                    e.getMostSpecificCause().getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    public ResponseEntity<ApiResponse<List<Usuario>>> findAll() {
        List<Usuario> listUsuarios = usuarioRepository.findAll();

        if (listUsuarios.isEmpty()) {
            ApiResponse<List<Usuario>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron usuarios.",
                    "La base de datos no tiene registros de usuarios.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<Usuario>> res = new ApiResponse<>(
                "OK",
                "Usuarios obtenidos correctamente.",
                null,
                listUsuarios
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<Usuario>> getById(Long idusuario) {
        Usuario usuario = usuarioRepository.findById(idusuario).orElse(null);

        if (usuario == null) {
            ApiResponse<Usuario> res = new ApiResponse<>(
                    "ERROR",
                    "Usuario no encontrado.",
                    "No existe un usuario con ese ID.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<Usuario> res = new ApiResponse<>(
                "OK",
                "Usuario obtenido correctamente.",
                null,
                usuario
        );
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    public ResponseEntity<ApiResponse<Ubicacion>> obtenerUbicacion(Long idusuario) {
        Ubicacion ubicacion = usuarioRepository.obtenerUbicacionPorUsuario(idusuario);

        if (ubicacion == null) {
            ApiResponse<Ubicacion> res = new ApiResponse<>(
                    "ERROR",
                    "Ubicación no encontrada.",
                    "No existe una ubicación registrada para este usuario.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<Ubicacion> res = new ApiResponse<>(
                "OK",
                "Ubicación obtenida correctamente.",
                null,
                ubicacion
        );
        return ResponseEntity.status(HttpStatus.OK).body(res);
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
                    "Credenciales incorrectas.",
                    "Usuario o contraseña incorrectos.",
                    null
            );
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
        } else if (verificacionGubernamental) {
            ApiResponse<Optional<UsuarioDto>> res = new ApiResponse<>(
                    "WARNING",
                    "Usuario gubernamental no verificado.",
                    "Verificación pendiente.",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        } else {
            ApiResponse<Optional<UsuarioDto>> res = new ApiResponse<>(
                    "OK",
                    "Inicio de sesión exitoso.",
                    null,
                    usuarioEncontrado
            );
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    public ResponseEntity<String> verificarGubernamental(String email, String token) {
        String tokenEmail = tokenService.validarToken(token);
        boolean isTokenEmailValid = email.equalsIgnoreCase(tokenEmail);
        boolean isTokenExpired = tokenService.tokenExpirado(token);

        String htmlVerificacionExitosa = VerificarGubernamentalTemplateEnum.VERIFICACION_EXITOSA.build(
                "EMAIL", email
        );

        String htmlVerificacionError = VerificarGubernamentalTemplateEnum.VERIFICACION_ERROR.build(
                "MENSAJE_ERROR", "Ocurrió un error al tratar de verificar el email: " + email
        );

        if(!isTokenExpired && isTokenEmailValid) {
            int filasAfectadas = usuarioRepository.actualizarEmpleadoGubVerificado(email, true);
            if (filasAfectadas >= 0) {
                return ResponseEntity.status(HttpStatus.OK).body(htmlVerificacionExitosa);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(htmlVerificacionError);
    }
}
