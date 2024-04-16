package com.jason.ventas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jason.ventas.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}
