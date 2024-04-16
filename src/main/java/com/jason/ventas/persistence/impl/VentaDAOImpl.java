package com.jason.ventas.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jason.ventas.model.Venta;
import com.jason.ventas.persistence.VentaDAO;
import com.jason.ventas.repositories.VentaRepository;

@Component
public class VentaDAOImpl implements VentaDAO {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> getAllVentas() {
        return (List<Venta>) ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void createVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Long id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            return ventaRepository.save(venta);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Double getGananciasAnuales(Long productoId, int year) {
        List<Venta> ventas = ventaRepository.findByProductoIdAndYear(productoId, year);

        // Calcula las ganancias totales
        double ganancias = 0.0;
        for (Venta venta : ventas) {
            ganancias += venta.getTotal() * venta.getProducto().getPrecio();
        }

        return ganancias;

    }

    @Override
    public Double getGananciasMensuales(Long productoId, int year, int month) {
        List<Venta> ventas = ventaRepository.findByProductoIdAndYearAndMonth(productoId, year, month);

        // Calcula las ganancias totales
        double ganancias = 0.0;
        for (Venta venta : ventas) {
            ganancias += venta.getTotal() * venta.getProducto().getPrecio();
        }

        return ganancias;
    }

    @Override
    public Double getGananciasDiarias(Long productoId, int year, int month, int day) {
        List<Venta> ventas = ventaRepository.findByProductoIdAndYearAndMonthAndDay(productoId, year, month, day);

        // Calcula las ganancias totales
        double ganancias = 0.0;
        for (Venta venta : ventas) {
            ganancias += venta.getTotal() * venta.getProducto().getPrecio();
        }

        return ganancias;
    }

}
