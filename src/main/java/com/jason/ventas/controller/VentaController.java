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

import com.jason.ventas.dto.VentaDTO;
import com.jason.ventas.model.Venta;
import com.jason.ventas.services.VentaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private static final Logger logger = LoggerFactory.getLogger(VentaController.class);

    @Autowired
    private VentaService ventaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable Long id) {
        logger.info("Obteniendo ventas con id {}", id);
        Optional<Venta> ventaOpcional = ventaService.getVentaById(id);

        if (ventaOpcional.isPresent()) {
            Venta venta = ventaOpcional.get();

            VentaDTO ventaDTO = VentaDTO.builder()
                    .id(venta.getId())
                    .fechaVenta(venta.getFechaVenta())
                    .total(venta.getTotal())
                    .productoId(venta.getProducto())
                    .build();
            logger.info("Venta encontrada: {}", ventaDTO);
            return ResponseEntity.ok(ventaDTO);
        } else {
            logger.info("Venta no encontrada");
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<?> getAllVentas() {
        logger.info("Obteniendo ventas");
        List<VentaDTO> ventaList = ventaService.getAllVentas()
                .stream()
                .map(venta -> VentaDTO.builder()
                        .id(venta.getId())
                        .fechaVenta(venta.getFechaVenta())
                        .total(venta.getTotal())
                        .productoId(venta.getProducto())
                        .build())
                .toList();
        return ResponseEntity.ok(ventaList);
    }

    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody VentaDTO ventaDTO) {

        logger.info("Creando venta: {}", ventaDTO);

        if (ventaDTO.getFechaVenta() == null || ventaDTO.getProductoId() == null){
            logger.error("La fecha de venta es requerida");
            return ResponseEntity.badRequest().body("Fecha de venta requerida");
        }

        ventaService.createVenta(Venta.builder()
                .fechaVenta(ventaDTO.getFechaVenta())
                .total(ventaDTO.getTotal())
                .producto(ventaDTO.getProductoId())
                .build());

        logger.info("Venta creada");

        return ResponseEntity.ok().body("Venta creada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        logger.info("Actualizando venta con id {}", id);
        Optional<Venta> ventaOpcional = ventaService.getVentaById(id);

        if (ventaOpcional.isPresent()) {
            Venta venta = ventaOpcional.get();

            venta.setFechaVenta(ventaDTO.getFechaVenta());
            venta.setTotal(ventaDTO.getTotal());

            ventaService.createVenta(venta);

            logger.info("Venta actualizada");

            return ResponseEntity.ok().body("Venta actualizada");
        }

        logger.warn("Venta no encontrada");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        logger.info("Eliminando venta con id {}", id);
        if (id != null) {
            ventaService.deleteById(id);
            logger.info("Venta eliminada");
            return ResponseEntity.ok().body("Venta eliminada");
        }
        logger.error("Id es requerido");
        return ResponseEntity.badRequest().body("Id requerido");
        //en este caso profesor deberia usar manejo de excepciones para que el usuario sepa que el id es requerido
    }

    @GetMapping("/{productoId}/ganancias/{year}")
    public ResponseEntity<?> getGananciasAnuales(@PathVariable Long productoId, @PathVariable int year) {
        logger.info("Obteniendo ganancias anuales para el producto {} en el año {}", productoId, year);
        Double ganancias = ventaService.getGananciasAnuales(productoId, year);
        if (ganancias != null) {
            logger.info("Ganancias encontradas: {}", ganancias);
            return ResponseEntity.ok().body("Las ganancias anuales del producto " + productoId + " en el año " + year + " son: " + ganancias+ " Pesos");
        }
        logger.warn("No se encontraron ganancias para el producto {} en el año {}", productoId, year);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productoId}/ganancias/{year}/{month}")
    public ResponseEntity<?> getGananciasMensuales(@PathVariable Long productoId, @PathVariable int year, @PathVariable int month) {
        logger.info("Obteniendo ganancias mensuales para el producto {} en el año {} y mes {}", productoId, year, month);
        Double ganancias = ventaService.getGananciasMensuales(productoId, year, month);
        if (ganancias != null) {
            logger.info("Ganancias encontradas: {}", ganancias);
            return ResponseEntity.ok().body("Las ganancias mensuales del producto " + productoId + " en el año " + year + " y mes " + month + " son: " + ganancias+ " Pesos");
        }
        logger.warn("No se encontraron ganancias para el producto {} en el año {} y mes {}", productoId, year, month);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productoId}/ganancias/{year}/{month}/{day}")
    public ResponseEntity<?> getGananciasDiarias(@PathVariable Long productoId, @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        logger.info("Obteniendo ganancias diarias para el producto {} en el año {} mes {} y dia {}", productoId, year, month, day);
        Double ganancias = ventaService.getGananciasDiarias(productoId, year, month, day);
        if (ganancias != null) {
            logger.info("Ganancias encontradas: {}", ganancias);
            return ResponseEntity.ok().body("Las ganancias diarias del producto " + productoId + " en el año " + year + " mes " + month + " y dia " + day + " son: " + ganancias+ " Pesos");
        }
        logger.warn("No se encontraron ganancias para el producto {} en el año {} mes {} y dia {}", productoId, year, month, day);
        return ResponseEntity.notFound().build();
    }

}
