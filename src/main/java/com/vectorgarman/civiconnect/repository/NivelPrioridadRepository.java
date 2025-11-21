package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.NivelPrioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelPrioridadRepository extends JpaRepository<NivelPrioridad, Long> {
}
