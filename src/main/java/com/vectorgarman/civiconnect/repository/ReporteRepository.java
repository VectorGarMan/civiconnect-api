package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.dto.Ubicacion;
import com.vectorgarman.civiconnect.entity.Reporte;
import com.vectorgarman.civiconnect.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
//    @Modifying
//    @Transactional
//    @Query("""
//    UPDATE Reporte r
//    SET r.empleadogubverificado = :valor
//    WHERE r.idreporte = :idreporte
//    """)
//    int actualizarEmpleadoGubVerificado(String email, Boolean valor);
//
//    Optional<Usuario> findByEmail(String email);
//
//    @Modifying
//    @Transactional
//    @Query("""
//    UPDATE Usuario u
//    SET u.contrasena = :nuevaContrasena
//    WHERE u.email = :email
//    """)
//    int actualizarContrasena(String email, String nuevaContrasena);
}
