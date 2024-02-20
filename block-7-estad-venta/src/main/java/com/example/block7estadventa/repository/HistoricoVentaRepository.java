package com.example.block7estadventa.repository;

import com.example.block7estadventa.domain.HistoricoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface HistoricoVentaRepository extends JpaRepository<HistoricoVenta, Integer> {

    @Query("SELECT h FROM HistoricoVenta h WHERE h.mes = :mes AND h.ano = :ano")
    Optional<List<HistoricoVenta>> findByMesAndAno(@Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT h FROM HistoricoVenta h WHERE h.ano = :ano")
    List<HistoricoVenta> findByAno(@Param("ano") int ano);

}

