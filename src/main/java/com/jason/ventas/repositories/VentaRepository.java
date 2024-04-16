package com.jason.ventas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jason.ventas.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
