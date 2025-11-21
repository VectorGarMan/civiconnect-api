package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Colonia;
import com.vectorgarman.civiconnect.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColoniaRepository extends JpaRepository<Colonia, Long> {
    List<Colonia> findByIdmunicipio(Long idmunicipio);
}
