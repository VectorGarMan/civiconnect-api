package com.vectorgarman.civiconnect.service;

import com.vectorgarman.civiconnect.dto.*;
import com.vectorgarman.civiconnect.entity.TipoUsuario;
import com.vectorgarman.civiconnect.entity.Usuario;
import com.vectorgarman.civiconnect.repository.TipoUsuarioRepository;
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

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Value("${spring.mail.destinatarioVerifGub}")
    private String destinatarioVerifGub;

    private void verificarGubernamentalEnviarCorreo(Usuario usuario) {
        String destinatario = this.destinatarioVerifGub;
        String asunto = "CiviConnect - Verificar Usuario Gubernamental";

        long duracion48Horas = 48 * 60 * 60 * 1000; // 48 horas en milisegundos

        String token = tokenService.generarTokenVerificacion(usuario.getEmail(), duracion48Horas);
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

            if(usuarioResponse.getIdtipousuario().equals(2L)){
                verificarGubernamentalEnviarCorreo(usuarioResponse);
            }

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

    public ResponseEntity<String> verificarGubernamentalValidToken(String email, String token) {
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

    public ResponseEntity<ApiResponse<Optional<Usuario>>> verificarCambioContrasena (String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if(usuario.isEmpty()){
            ApiResponse<Optional<Usuario>> res = new ApiResponse<>(
                    "ERROR",
                    "No fue posible recuperar un usuario con el email dado.",
                    "Usuario no encontrado.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        ApiResponse<Optional<Usuario>> res = new ApiResponse<>(
                "OK",
                "Usuario recuperado con el email dado.",
                "Usuario encontrado.",
                usuario
        );

        String destinatario = usuario.get().getEmail();
        String asunto = "CiviConnect - Solicitud de Cambio de Contraseña";

        long duracion15Minutos = 15 * 60 * 1000; // 15 minutos en milisegundos

        String token = tokenService.generarTokenVerificacion(usuario.get().getEmail(), duracion15Minutos);

        String mensaje = VerificarGubernamentalTemplateEnum.VERIFICACION_CAMBIO_CONTRASENA.build(
                "EMAIL", usuario.get().getEmail(),
                "TOKEN", token
        );

        mailService.enviarCorreo(destinatario, asunto, mensaje);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    public ResponseEntity<ApiResponse<?>> cambiarContrasena (CambioContrasenaRequest cambioContrasenaRequest) {
        String tokenEmail = tokenService.validarToken(cambioContrasenaRequest.getToken());
        boolean isTokenEmailValid = cambioContrasenaRequest.getEmail().equalsIgnoreCase(tokenEmail);
        boolean isTokenExpired = tokenService.tokenExpirado(cambioContrasenaRequest.getToken());

        if(!isTokenExpired && isTokenEmailValid) {
            int filasAfectadas = usuarioRepository.actualizarContrasena(cambioContrasenaRequest.getEmail(), cambioContrasenaRequest.getNuevaContrasena());
            if (filasAfectadas >= 0) {

                ApiResponse<Optional<Usuario>> res = new ApiResponse<>(
                        "OK",
                        "La contraseña ha sido actualizada.",
                        "Contraseña actualizada.",
                        null
                );
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }
        ApiResponse<Optional<Usuario>> res = new ApiResponse<>(
                "ERROR",
                "Token inválido o expirado.",
                "Error de token.",
                null
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

    public ResponseEntity<ApiResponse<List<TipoUsuario>>> findAllTipoUsuario() {
        List<TipoUsuario> listTiposUsuario = tipoUsuarioRepository.findAll();

        if (listTiposUsuario.isEmpty()) {
            ApiResponse<List<TipoUsuario>> res = new ApiResponse<>(
                    "ERROR",
                    "No se encontraron tipos de usuario.",
                    "La base de datos no tiene registros de tipos de usuario.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }

        ApiResponse<List<TipoUsuario>> res = new ApiResponse<>(
                "OK",
                "Tipos de usuario obtenidos correctamente.",
                null,
                listTiposUsuario
        );

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
