package com.acme.hr.service;

import java.util.List;

import com.acme.hr.model.Projeto;
import com.acme.hr.repository.ProjetoRepository;
import com.acme.hr.exception.EntidadeNaoEncontradaException;

public class ProjetoService {

    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Projeto projeto) {

        repository.save(projeto);
    }


    public List<Projeto> listarTodos() {
        return repository.findAll();
    }

    public Projeto buscarPorId(String id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Projeto não encontrado: " + id
                        )
                );
    }
    public void remover(String id) {

        buscarPorId(id);

        repository.deleteById(id);
    }
}
