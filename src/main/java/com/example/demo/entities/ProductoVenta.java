package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoVenta {

    @EmbeddedId
    private ProductoVentaId id;

    @ManyToOne
    @MapsId("ventaId")
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    @Column(precision = 10, scale = 0)
    private BigDecimal precio;
}
