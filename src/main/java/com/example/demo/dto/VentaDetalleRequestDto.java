package com.example.demo.dto;

import lombok.Data;

@Data
public class VentaDetalleRequestDto {
	
	private Integer idProducto;
	private Integer cantidad;
	private Double precio;

}
