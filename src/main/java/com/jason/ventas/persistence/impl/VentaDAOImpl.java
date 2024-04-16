package com.jason.ventas.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jason.ventas.model.Venta;
import com.jason.ventas.persistence.VentaDAO;
import com.jason.ventas.repositories.VentaRepository;

public class VentaDAOImpl implements VentaDAO{

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
    
}
