package com.jason.ventas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Builder
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private LocalDate fechaVenta;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Producto producto;
    
}
