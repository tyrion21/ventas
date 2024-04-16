package com.jason.ventas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jason.ventas.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v WHERE v.producto.id = :productoId AND YEAR(v.fechaVenta) = :year")
    List<Venta> findByProductoIdAndYear(@Param("productoId") Long productoId, @Param("year") int year);

    @Query("SELECT v FROM Venta v WHERE v.producto.id = :productoId AND YEAR(v.fechaVenta) = :year AND MONTH(v.fechaVenta) = :month")
    List<Venta> findByProductoIdAndYearAndMonth(@Param("productoId") Long productoId, @Param("year") int year,
    @Param("month") int month);

    @Query("SELECT v FROM Venta v WHERE v.producto.id = :productoId AND YEAR(v.fechaVenta) = :year AND MONTH(v.fechaVenta) = :month AND DAY(v.fechaVenta) = :day")
    List<Venta> findByProductoIdAndYearAndMonthAndDay(@Param("productoId") Long productoId, @Param("year") int year,
    @Param("month") int month, @Param("day") int day);

}
