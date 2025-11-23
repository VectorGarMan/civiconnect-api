package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByIdreporte(Long idreporte);

    List<Comentario> findByIdusuario(Long idusuario);

    List<Comentario> findByIdcomentariopadre(Long idcomentariopadre);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comentario c WHERE c.idcomentario = :idcomentario AND c.idusuario = :idusuario")
    int deleteByIdAndUsuario(@Param("idcomentario") Long idcomentario, @Param("idusuario") Long idusuario);
}
