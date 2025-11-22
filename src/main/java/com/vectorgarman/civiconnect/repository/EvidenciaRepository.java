package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Colonia;
import com.vectorgarman.civiconnect.entity.Evidencia;
import com.vectorgarman.civiconnect.entity.TipoUsuario;
import com.vectorgarman.civiconnect.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    List<Evidencia> findByIdreporte(Long idreporte);
}
