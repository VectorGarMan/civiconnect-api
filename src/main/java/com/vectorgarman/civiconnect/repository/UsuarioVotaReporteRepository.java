package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.dto.UsuarioVotaReporteId;
import com.vectorgarman.civiconnect.entity.UsuarioVotaReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioVotaReporteRepository extends JpaRepository<UsuarioVotaReporte, UsuarioVotaReporteId> {
    List<UsuarioVotaReporte> findByIdIdusuario(Long idusuario);
}
