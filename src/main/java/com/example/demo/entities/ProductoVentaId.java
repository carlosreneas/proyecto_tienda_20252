package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoVentaId implements Serializable {

    private Integer ventaId;
    private Integer productoId;
    
 // equals y hashCode obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoVentaId)) return false;
        ProductoVentaId that = (ProductoVentaId) o;
        return Objects.equals(productoId, that.productoId) &&
               Objects.equals(ventaId, that.ventaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, ventaId);
    }
}

