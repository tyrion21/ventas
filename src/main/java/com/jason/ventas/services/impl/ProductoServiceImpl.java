package com.jason.ventas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.ventas.model.Producto;
import com.jason.ventas.persistence.ProductoDAO;
import com.jason.ventas.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoDAO productoDAO;

    @Override
    public List<Producto> getAllProducts() {
        return productoDAO.getAllProductos();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return productoDAO.getProductoById(id);
    }

    @Override
    public void createProducto(Producto producto) {
        productoDAO.createProducto(producto);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        return productoDAO.updateProducto(id, producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoDAO.deleteById(id);
    }
}
