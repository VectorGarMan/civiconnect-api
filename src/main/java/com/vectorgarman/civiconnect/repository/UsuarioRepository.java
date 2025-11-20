package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.dto.UsuarioDto;
import com.vectorgarman.civiconnect.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = """
        SELECT new com.vectorgarman.civiconnect.dto.Ubicacion(
            c.idcolonia,
            c.nombre,
            m.idmunicipio,
            m.nombre,
            e.idestado,
            e.nombre
        )
        FROM Usuario u
        JOIN Colonia c ON u.idcolonia = c.idcolonia
        JOIN Municipio m ON c.idmunicipio = m.idmunicipio
        JOIN Estado e ON m.idestado = e.idestado
        WHERE u.idusuario = :idusuario
        """)
    Ubicacion obtenerUbicacionPorUsuario(Long idusuario);

    @Query("""
        SELECT new com.vectorgarman.civiconnect.dto.UsuarioDto(
            u.idusuario,
            u.idtipousuario,
            u.idcolonia,
            u.nombreusuario,
            u.fecharegistro,
            u.empleadogubverificado,
            u.email
        )
        FROM Usuario u
        WHERE u.email = :email
        AND u.contrasena = :contrasena
    """)
    Optional<UsuarioDto> findByEmailAndContrasena(String email, String contrasena);

    @Modifying
    @Transactional
    @Query("""
    UPDATE Usuario u
    SET u.empleadogubverificado = :valor
    WHERE u.email = :email
    """)
    int actualizarEmpleadoGubVerificado(String email, Boolean valor);

}
