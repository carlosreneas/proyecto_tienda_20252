package com.example.demo.services;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(String documento) {
        return clienteRepository.findById(documento);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(String documento, Cliente clienteDetails) {
        return clienteRepository.findById(documento)
                .map(cliente -> {
                    cliente.setNombre(clienteDetails.getNombre());
                    cliente.setFechaNacimiento(clienteDetails.getFechaNacimiento());
                    cliente.setEmail(clienteDetails.getEmail());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente not found with documento: " + documento));
    }

    public void deleteCliente(String documento) {
        if (!clienteRepository.existsById(documento)) {
            throw new RuntimeException("Cliente not found with documento: " + documento);
        }
        clienteRepository.deleteById(documento);
    }
}
