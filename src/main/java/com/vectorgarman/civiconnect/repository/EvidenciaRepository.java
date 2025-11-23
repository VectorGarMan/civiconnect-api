package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
    List<Evidencia> findByIdreporte(Long idreporte);
}
