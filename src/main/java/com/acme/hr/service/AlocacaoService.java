package com.acme.hr.service;

import java.util.List;


import com.acme.hr.model.Cliente;
import com.acme.hr.model.Alocacao;
import com.acme.hr.model.Funcionario;
import com.acme.hr.exception.EntidadeNaoEncontradaException;
import com.acme.hr.repository.ClienteRepositoryMemoria;
import com.acme.hr.repository.AlocacaoRepositoryMemoria;
import com.acme.hr.repository.FuncionarioRepositoryMemoria;

public class AlocacaoService {

    private final ClienteRepositoryMemoria clienteRepository;
    private final FuncionarioRepositoryMemoria funcionarioRepository;
    private final AlocacaoRepositoryMemoria alocacaoRepository;

    public AlocacaoService(
            ClienteRepositoryMemoria clienteRepository,
            FuncionarioRepositoryMemoria funcionarioRepository,
            AlocacaoRepositoryMemoria alocacaoRepository) {

        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.alocacaoRepository = alocacaoRepository;
    }

    public Alocacao criarAlocacao(
            String id,
            String clienteId,
            String funcionarioId,
            int dias) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Cliente não encontrado: " + clienteId
                        )
                );

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Veículo não encontrado: " + funcionarioId
                        )
                );

        Alocacao alocacao = new Alocacao(
                id,
                cliente,
                funcionario,
                dias
        );

        funcionario.alugar();

        alocacaoRepository.save(alocacao);

        return alocacao;
    }

    public Alocacao buscarPorId(String id) {

        return alocacaoRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Locação não encontrada: " + id
                        )
                );
    }

    public List<Alocacao> listarTodas() {

        return alocacaoRepository.findAll();
    }

    public void devolverAlocacao(String id) {

        Alocacao alocacao = buscarPorId(id);

        alocacao.finalizar();

        alocacao.getFuncionario().devolver();
    }

    public List<Alocacao> listarAtivas() {

        return alocacaoRepository.findAll()
                .stream()
                .filter(Alocacao::isAtiva)
                .toList();
    }
}
