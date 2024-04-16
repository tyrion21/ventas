package com.jason.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.jason.ventas.dto.ProductoDTO;
import com.jason.ventas.model.Producto;
import com.jason.ventas.services.ProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        logger.info("Obteniendo producto con id {}", id);
        Optional<Producto> productoOpcional = productoService.getProductoById(id);

        if (productoOpcional.isPresent()) {
            Producto producto = productoOpcional.get();

            ProductoDTO productoDTO = ProductoDTO.builder()
                    .id(producto.getId())
                    .nombre(producto.getNombre())
                    .precio(producto.getPrecio())
                    .ventaList(producto.getVentaList())
                    .build();
            logger.info("Producto encontrado: {}", productoDTO);
            return ResponseEntity.ok(productoDTO);
        } else {
            logger.info("Producto no encontrado");
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        logger.info("Obteniendo productos");
        List<ProductoDTO> productoList = productoService.getAllProducts()
                .stream()
                .map(producto -> ProductoDTO.builder()
                        .id(producto.getId())
                        .nombre(producto.getNombre())
                        .precio(producto.getPrecio())
                        .ventaList(producto.getVentaList())
                        .build())
                .toList();
        return ResponseEntity.ok(productoList);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductoDTO productoDto) {

        logger.info("Creando producto: {}", productoDto);

        if (productoDto.getNombre().isBlank()) {
            logger.error("El titulo es requerido");
            return ResponseEntity.badRequest().body("El titulo es requerido");
        }

        productoService.createProducto(Producto.builder()
                .nombre(productoDto.getNombre())
                .precio(productoDto.getPrecio())
                .build());

        logger.info("Producto creado");

        return ResponseEntity.ok().body("Producto creado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDto) {
        logger.info("Actualizando producto con id {}", id);
        Optional<Producto> productoOpcional = productoService.getProductoById(id);

        if (productoOpcional.isPresent()) {
            Producto producto = productoOpcional.get();

            producto.setNombre(productoDto.getNombre());
            producto.setPrecio(productoDto.getPrecio());

            productoService.createProducto(producto);

            logger.info("Producto actualizado");

            return ResponseEntity.ok().body("Producto actualizado");
        }

        logger.warn("Producto no encontrado");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        logger.info("Eliminando producto con id {}", id);
        if (id != null) {
            productoService.deleteProducto(id);
            logger.info("Producto eliminado");
            return ResponseEntity.ok().body("Producto eliminado");
        }
        logger.error("Id es requerido");
        return ResponseEntity.badRequest().build();
    }
    
}
