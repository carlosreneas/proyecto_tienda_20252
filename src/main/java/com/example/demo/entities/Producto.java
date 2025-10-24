package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "text")
    private String descripcion;

    private Integer cantidad;

    @Column(precision = 10, scale = 0)
    private java.math.BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Categoria categoria;
}
