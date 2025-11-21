package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, Long> {
}
