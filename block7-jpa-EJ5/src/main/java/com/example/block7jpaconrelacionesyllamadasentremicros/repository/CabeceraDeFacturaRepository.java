package com.example.block7jpaconrelacionesyllamadasentremicros.repository;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CabeceraDeFacturaRepository  extends JpaRepository<CabeceraDeFactura, Integer> {

    @Query("SELECT f FROM CabeceraDeFactura f JOIN f.lineasDeFactura l WHERE l.producto.idProducto = :codigo")
    Optional<List<CabeceraDeFactura>> findByCodigoDeProducto(@Param("codigo") Long codigo);
    @Query("SELECT f FROM CabeceraDeFactura f WHERE f.cliente.dni = :idCliente AND f.fechaFactura BETWEEN :fechaInicio AND :fechaFin")
    Optional<List<CabeceraDeFactura>> findByFacturaWithClienteAndRangoDeFechas(@Param("idCliente") String idCliente, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
    @Query("SELECT f FROM CabeceraDeFactura f WHERE f.idFactura = :id")
    Optional<CabeceraDeFactura> findByIdCabecera(@Param("id") Integer id);

    @Query("SELECT f FROM CabeceraDeFactura f WHERE f.cliente.dni = :dniCliente")
    Optional<List<CabeceraDeFactura>> findByDniCliente(@Param("dniCliente") String dniCliente);
    @Query("SELECT f FROM CabeceraDeFactura f WHERE FUNCTION('YEAR', f.fechaFactura) = :anio")
    Optional<List<CabeceraDeFactura>> findByYear(@Param("anio") int anio);

    @Query("SELECT f FROM CabeceraDeFactura f WHERE FUNCTION('YEAR', f.fechaFactura) = :anio AND FUNCTION('MONTH', f.fechaFactura) = :mes")
    Optional<List<CabeceraDeFactura>> findByMonthAndYear(@Param("mes") int mes, @Param("anio") int anio);




}
