package com.acme.hr.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.acme.hr.model.Projeto;

public class ProjetoRepository
        implements Repository<Projeto, String> {

    private final Map<String, Projeto> projetos = new HashMap<>();

    @Override
    public void save(Projeto cliente) {
        projetos.put(cliente.getId(), cliente);
    }

    @Override
    public Optional<Projeto> findById(String id) {
        return Optional.ofNullable(projetos.get(id));
    }

    @Override
    public List<Projeto> findAll() {
        return new ArrayList<>(projetos.values());
    }

    @Override
    public void deleteById(String id) {
        projetos.remove(id);
    }
}
