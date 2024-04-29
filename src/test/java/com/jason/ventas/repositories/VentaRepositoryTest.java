package com.jason.ventas.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jason.ventas.model.Producto;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VentaRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void testSaveProducto() {
        Producto producto = new Producto();
        producto.setNombre("Nombre de prueba");
        producto.setPrecio(100.0);
        productoRepository.save(producto);

        assertNotNull(producto.getId());
        assertEquals("Nombre de prueba", producto.getNombre());
        assertEquals(100.0, producto.getPrecio());
    }

    @Test
    public void updateProducto() {
        Producto producto = new Producto();
        producto.setNombre("Nombre de prueba");
        producto.setPrecio(100.0);
        productoRepository.save(producto);

        producto.setNombre("Nombre de prueba 2");
        producto.setPrecio(200.0);
        productoRepository.save(producto);

        Producto productoActualizado = productoRepository.findById(producto.getId()).get();
        assertEquals("Nombre de prueba 2", productoActualizado.getNombre());
        assertEquals(200.0, productoActualizado.getPrecio());
    }
}