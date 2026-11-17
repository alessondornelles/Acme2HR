package com.acme.hr.service;

import java.util.List;

import com.acme.hr.model.Funcionario;
import com.acme.hr.exception.EntidadeNaoEncontradaException;
import com.acme.hr.exception.CadastroDuplicadoException;
import com.acme.hr.repository.FuncionarioRepository;

public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Funcionario funcionario) {

        boolean nomeExiste = repository.findAll()
                .stream()
                .anyMatch(v -> v.getNome().equalsIgnoreCase(
                        funcionario.getNome()
                ));

        if (nomeExiste) {
            throw new CadastroDuplicadoException(
                    "Já existe um funcionário cadastrado com esta nome."
            );
        }

        repository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }
    public List<Funcionario> listarDisponiveis() {
        return repository.findAll()
                .stream()
                .filter(Funcionario::isDisponivel)
                .toList();
    }

    public Funcionario buscarPorId(String id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Funcionário não encontrado: " + id
                        )
                );
    }

    public void remover(String id) {

        // Busca o funcionário e verifica se ele existe.
        Funcionario funcionario = buscarPorId(id);

        // Impede a remoção de funcionários alugados.
        if (!funcionario.isDisponivel()) {
            throw new IllegalStateException(
                    "Não é possível remover um funcionário que está alugado."
            );
        }

        // Remove o funcionário.
        repository.deleteById(id);
    }
}
