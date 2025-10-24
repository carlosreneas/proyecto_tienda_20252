package com.example.demo.services;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Producto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo;

    public ProductoService(ProductoRepository productoRepo, CategoriaRepository categoriaRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<Producto> findAll() {
        return productoRepo.findAll();
    }

    public Producto findById(Integer id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto not found: " + id));
    }
    /*
    public List<Producto> findByCategoria(Integer categoriaId) {
        return productoRepo.findByTipoId(categoriaId);
    }
	*/
    public List<Producto> searchByNombre(String q) {
        return productoRepo.findByNombreContainingIgnoreCase(q);
    }

    public Producto create(Producto in, Integer categoriaId) {
        Categoria cat = categoriaRepo.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria not found: " + categoriaId));
        in.setId(null);
        // si tu relación es ManyToOne Categoria tipo; o si solo guardas tipoId:
        in.setCategoria(cat); // o in.setTipoId(categoriaId);
        return productoRepo.save(in);
    }

    public Producto update(Integer id, Producto in, Integer categoriaId) {
        Producto p = findById(id);
        p.setNombre(in.getNombre());
        p.setDescripcion(in.getDescripcion());
        p.setCantidad(in.getCantidad());
        p.setPrecio(in.getPrecio());

        if (categoriaId != null) {
            Categoria cat = categoriaRepo.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException("Categoria not found: " + categoriaId));
            p.setCategoria(cat); // o p.setTipoId(categoriaId);
        }
        return productoRepo.save(p);
    }

    public void delete(Integer id) {
        if (!productoRepo.existsById(id)) {
            throw new RuntimeException("Producto not found: " + id);
        }
        productoRepo.deleteById(id);
    }
}
