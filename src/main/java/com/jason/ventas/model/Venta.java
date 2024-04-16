package com.jason.ventas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Venta {
    
    private Long id;
    private LocalDate fechaVenta;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
}
