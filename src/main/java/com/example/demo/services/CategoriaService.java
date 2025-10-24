package com.example.demo.services;

import com.example.demo.entities.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    public List<Categoria> findAll() {
        return repo.findAll();
    }

    public Categoria findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria not found: " + id));
    }

    public Categoria create(Categoria c) {
        c.setId(null);
        return repo.save(c);
    }

    public Categoria update(Integer id, Categoria in) {
        Categoria c = findById(id);
        c.setDescripcion(in.getDescripcion()); // o setDescripion(...) si tu campo se llama así
        return repo.save(c);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Categoria not found: " + id);
        }
        repo.deleteById(id);
    }
}
