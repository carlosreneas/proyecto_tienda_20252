package com.example.demo.controllers;

import com.example.demo.entities.Producto;
import com.example.demo.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // Listado general y filtros opcionales: ?categoriaId=1&search=zapato
    @GetMapping
    public List<Producto> list(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) String search
    ) {
        //if (categoriaId != null) return service.findByCategoria(categoriaId);
        if (search != null && !search.isBlank()) return service.searchByNombre(search);
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear producto: recibe JSON del producto y categoriaId como query o path
    @PostMapping
    public ResponseEntity<Producto> create(
            @RequestParam Integer categoriaId,
            @RequestBody Producto in
    ) {
        try {
            return ResponseEntity.ok(service.create(in, categoriaId));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar producto: permite cambiar la categoría opcionalmente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer categoriaId,
            @RequestBody Producto in
    ) {
        try {
            return ResponseEntity.ok(service.update(id, in, categoriaId));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
