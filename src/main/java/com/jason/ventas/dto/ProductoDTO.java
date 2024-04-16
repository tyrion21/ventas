package com.jason.ventas.dto;


import java.util.List;
import java.util.ArrayList;

import com.jason.ventas.model.Venta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    @Builder.Default
    private List<Venta> ventaList = new ArrayList<>();
}



