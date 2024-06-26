package com.jason.ventas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.ventas.model.Venta;
import com.jason.ventas.persistence.VentaDAO;
import com.jason.ventas.services.VentaService;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaDAO ventaDAO;

    @Override
    public List<Venta> getAllVentas() {
        return ventaDAO.getAllVentas();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaDAO.getVentaById(id);
    }

    @Override
    public void createVenta(Venta venta) {
        ventaDAO.createVenta(venta);
    }

    @Override
    public Venta updateVenta(Long id, Venta venta) {
        return ventaDAO.updateVenta(id, venta);
    }

    @Override
    public void deleteById(Long id) {
        ventaDAO.deleteById(id);
    }

    @Override
    public Double getGananciasAnuales(Long productoId, int year) {
        return ventaDAO.getGananciasAnuales(productoId, year);
    }

    @Override
    public Double getGananciasMensuales(Long productoId, int year, int month) {
        return ventaDAO.getGananciasMensuales(productoId, year, month);
    }

    @Override
    public Double getGananciasDiarias(Long productoId, int year, int month, int day) {
        return ventaDAO.getGananciasDiarias(productoId, year, month, day);
    }

    
}
