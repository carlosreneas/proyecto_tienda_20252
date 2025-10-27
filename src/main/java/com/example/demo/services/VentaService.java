package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VentaDetalleRequestDto;
import com.example.demo.dto.VentaRequestDto;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Producto;
import com.example.demo.entities.ProductoVenta;
import com.example.demo.entities.ProductoVentaId;
import com.example.demo.entities.Venta;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	VentaRepository ventaRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Transactional
	public String registrarVenta(VentaRequestDto venta) {
		
		String documento = venta.getDocumento();
		
		if (!clienteRepository.existsById(documento)) {
            throw new RuntimeException("Cliente not found with documento: " + documento);
        }
		
		Cliente cliente = clienteRepository.findById(documento).orElse(null);
		
		List<VentaDetalleRequestDto> detalles = venta.getDetalle();
		
		Venta ventaNew = new Venta();
		ventaNew.setCliente(cliente);
		ventaNew.setFecha(LocalDate.now());	
		ventaNew.setProductos(new ArrayList<ProductoVenta>());
		
		Venta ventaCreada = ventaRepository.save(ventaNew);
		
		Double totalVenta = 0D;
		
		for(VentaDetalleRequestDto detalle: detalles) {
			
			Integer idProducto = detalle.getIdProducto();
			
			if (!productoRepository.existsById(idProducto)) {
	            throw new RuntimeException("Producto not found: " + idProducto);
	        }
			
			Producto producto = productoRepository.findById(idProducto).orElse(null);
			
			if(producto.getCantidad() < detalle.getCantidad()) {
				throw new RuntimeException("No ha suficientes existencias del producto: " + idProducto);
			}
			System.out.println(producto.getNombre());
			ProductoVenta productoVenta = new ProductoVenta();
			productoVenta.setProducto(producto);
			productoVenta.setVenta(ventaNew);
			productoVenta.setCantidad(detalle.getCantidad());
			productoVenta.setPrecio(detalle.getPrecio());
			
			ProductoVentaId productoVentaId = new ProductoVentaId();
			productoVentaId.setProductoId(idProducto);
			productoVentaId.setVentaId(ventaCreada.getId());
			productoVenta.setId(productoVentaId);
			
			totalVenta += detalle.getCantidad() * detalle.getPrecio();
			
			ventaNew.getProductos().add(productoVenta);
			
		}
		
		
		ventaCreada.setTotal(totalVenta);
		ventaRepository.save(ventaCreada);
		
		
		return "Venta finalizada";
	}
}
