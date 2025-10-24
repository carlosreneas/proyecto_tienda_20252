package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(length = 20)
    private String documento;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name="fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 100)
    private String email;
}
