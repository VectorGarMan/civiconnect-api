package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.TipoArchivo;
import com.vectorgarman.civiconnect.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
}
