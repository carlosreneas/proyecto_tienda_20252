package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoVentaId implements Serializable {

    private Integer ventaId;
    private Integer productoId;
}

