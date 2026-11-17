package com.acme.hr.service;

import java.util.List;


import com.acme.hr.model.Projeto;
import com.acme.hr.model.Alocacao;
import com.acme.hr.model.Funcionario;
import com.acme.hr.exception.EntidadeNaoEncontradaException;
import com.acme.hr.repository.ProjetoRepository;
import com.acme.hr.repository.AlocacaoRepository;
import com.acme.hr.repository.FuncionarioRepository;

public class AlocacaoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AlocacaoRepository alocacaoRepository;

    public AlocacaoService(
            ProjetoRepository projetoRepository,
            FuncionarioRepository funcionarioRepository,
            AlocacaoRepository alocacaoRepository) {

        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.alocacaoRepository = alocacaoRepository;
    }

    public Alocacao criarAlocacao(
            String id,
            String projetoId,
            String funcionarioId,
            int dias) {

        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Projeto não encontrado: " + projetoId
                        )
                );

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() ->
                        new EntidadeNaoEncontradaException(
                                "Funcionário não encontrado: " + funcionarioId
                        )
                );

        Alocacao alocacao = new Alocacao(
                id,
                projeto,
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
                                "Alocação não encontrada: " + id
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
