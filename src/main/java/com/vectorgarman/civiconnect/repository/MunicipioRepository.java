package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Estado;
import com.vectorgarman.civiconnect.entity.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    List<Municipio> findByIdestado(Long idestado);
}
