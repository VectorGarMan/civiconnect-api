package com.vectorgarman.civiconnect.repository;

import com.vectorgarman.civiconnect.entity.ReporteView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteViewRepository extends JpaRepository<ReporteView, Long> {
    List<ReporteView> findByIdreporteIn(List<Long> ids);
}
