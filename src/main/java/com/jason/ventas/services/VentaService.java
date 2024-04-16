package com.jason.ventas.services;

import java.util.List;
import java.util.Optional;

import com.jason.ventas.model.Venta;

public interface VentaService {
    
    List<Venta> getAllVentas();

    Optional<Venta> getVentaById(Long id);

    void createVenta(Venta venta);

    Venta updateVenta(Long id, Venta venta);

    void deleteById(Long id);

    Double getGananciasAnuales(Long productoId, int year);

    Double getGananciasMensuales(Long productoId, int year, int month);

    Double getGananciasDiarias(Long productoId, int year, int month, int day);
}
