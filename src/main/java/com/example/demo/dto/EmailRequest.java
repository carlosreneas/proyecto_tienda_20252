package com.example.demo.dto;

import lombok.Data;

@Data
public class EmailRequest {

    private String destino;
    private String nombre;
    private String titulo;
    private String mensaje;


}

