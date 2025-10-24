package com.example.demo.repositories;

import com.example.demo.entities.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	//public List<Producto> findByTipoId(Integer categoriaId);
	public List<Producto> findByNombreContainingIgnoreCase(String q); 
	
}
