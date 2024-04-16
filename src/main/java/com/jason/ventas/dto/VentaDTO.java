package com.jason.ventas.dto;

import com.jason.ventas.model.Producto;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class VentaDTO {

    private Long id;
    private LocalDate fechaVenta;
    private Double total;
    private Producto productoId;

}
