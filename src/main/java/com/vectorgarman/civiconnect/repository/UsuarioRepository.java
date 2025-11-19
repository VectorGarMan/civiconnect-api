package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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


    Usuario findByEmail(String email);
}
