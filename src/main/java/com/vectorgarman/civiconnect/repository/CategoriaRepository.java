package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Categoria;
import com.vectorgarman.civiconnect.entity.EstadoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
