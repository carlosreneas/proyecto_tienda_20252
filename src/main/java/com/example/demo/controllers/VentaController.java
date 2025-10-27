package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VentaRequestDto;
import com.example.demo.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
	
	@Autowired
	VentaService ventaService;
	
	@PostMapping
	public String realizarVenta(@RequestBody VentaRequestDto ventaDto) {
		
		return ventaService.registrarVenta(ventaDto);
	}

}
