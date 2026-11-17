package com.acme.hr.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.acme.hr.model.Cliente;

public class ClienteRepositoryMemoria
        implements Repository<Cliente, String> {

    private final Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public void save(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public void deleteById(String id) {
        clientes.remove(id);
    }
}
