package com.acme.hr.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.acme.hr.model.Alocacao;

public class AlocacaoRepositoryMemoria implements Repository<Alocacao, String> {

    private final Map<String, Alocacao> alocacoes = new HashMap<>();

    @Override
    public void save(Alocacao alocacao) {
        alocacoes.put(alocacao.getId(), alocacao);
    }

    @Override
    public Optional<Alocacao> findById(String id) {
        return Optional.ofNullable(alocacoes.get(id));
    }

    @Override
    public List<Alocacao> findAll() {
        return new ArrayList<>(alocacoes.values());
    }

    @Override
    public void deleteById(String id) {
        alocacoes.remove(id);
    }
}