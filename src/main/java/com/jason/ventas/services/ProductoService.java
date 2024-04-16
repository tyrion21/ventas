package com.jason.ventas.services;

import java.util.List;
import java.util.Optional;

import com.jason.ventas.model.Producto;

public interface ProductoService {

    List<Producto> getAllProducts();

    Optional<Producto> getProductoById(Long id);

    void createProducto(Producto producto);

    Producto updateProducto(Long id, Producto producto);

    void deleteProducto(Long id);
}
