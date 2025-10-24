package com.example.demo.repositories;

import com.example.demo.entities.ProductoVenta;
import com.example.demo.entities.ProductoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, ProductoVentaId> {
}
