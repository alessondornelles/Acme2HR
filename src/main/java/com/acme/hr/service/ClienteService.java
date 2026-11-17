package com.acme.hr.service;

import java.util.List;

import com.acme.hr.model.Cliente;
import com.acme.hr.exception.CadastroDuplicadoException;
import com.acme.hr.repository.ClienteRepositoryMemoria;
import com.acme.hr.exception.EntidadeNaoEncontradaException;

public class ClienteService {

    private final ClienteRepositoryMemoria repository;

    public ClienteService(ClienteRepositoryMemoria repository) {
        this.repository = repository;
    }

    public void cadastrar(Cliente cliente) {

        boolean cpfExiste = repository.findAll()
                .stream()
                .anyMatch(c -> c.getCpf().equals(cliente.getCpf()));

        if (cpfExiste) {
            throw new CadastroDuplicadoException(
                    "Já existe um cliente cadastrado com este CPF."
            );
        }

        repository.save(cliente);
    }


    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(String id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Cliente não encontrado: " + id
                        )
                );
    }
    public void remover(String id) {

        buscarPorId(id);

        repository.deleteById(id);
    }
}
