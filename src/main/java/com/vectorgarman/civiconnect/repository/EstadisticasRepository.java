package com.vectorgarman.civiconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vectorgarman.civiconnect.entity.Estadisticas;

@Repository
public interface EstadisticasRepository extends JpaRepository<Estadisticas, Long> {
}
