package com.jason.ventas.persistence;

import java.util.List;
import java.util.Optional;

import com.jason.ventas.model.Producto;

public interface ProductoDAO {

    List<Producto> getAllProductos();

    Optional<Producto> getProductoById(Long id);

    Producto updateProducto(Long id, Producto producto);

    void createProducto(Producto producto);

    void deleteById(Long id);
}
