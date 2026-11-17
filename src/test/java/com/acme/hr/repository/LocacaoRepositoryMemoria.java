package br.edu.locadora.repository;

import br.edu.locadora.domain.Carro;
import br.edu.locadora.domain.Cliente;
import br.edu.locadora.domain.Locacao;
import br.edu.locadora.domain.Veiculo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LocacaoRepositoryMemoriaTest {

    private Locacao criarLocacao(String id) {
        Cliente cliente = new Cliente(
                "C01", "João Silva",
                "123.456.789-00",
                "joao@email.com", "48999999999"
        );

        Veiculo veiculo = new Carro(
                "V01", "ABC-1234", "Fiat",
                "Argo", 2022, new BigDecimal("150.00"), 4
        );

        return new Locacao(id, cliente, veiculo, 3);
    }

    @Test
    void deveSalvarELocalizarLocacao() {
        LocacaoRepositoryMemoria repository = new LocacaoRepositoryMemoria();
        Locacao locacao = criarLocacao("L01");

        repository.save(locacao);

        assertTrue(repository.findById("L01").isPresent());
    }

    @Test
    void deveRetornarVazioQuandoLocacaoNaoExistir() {
        LocacaoRepositoryMemoria repository = new LocacaoRepositoryMemoria();

        assertTrue(repository.findById("L99").isEmpty());
    }

    @Test
    void deveListarTodasLocacoes() {
        LocacaoRepositoryMemoria repository = new LocacaoRepositoryMemoria();

        repository.save(criarLocacao("L01"));
        repository.save(criarLocacao("L02"));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void deveExcluirLocacao() {
        LocacaoRepositoryMemoria repository = new LocacaoRepositoryMemoria();
        repository.save(criarLocacao("L01"));

        repository.deleteById("L01");

        assertTrue(repository.findById("L01").isEmpty());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverLocacoes() {
        LocacaoRepositoryMemoria repository = new LocacaoRepositoryMemoria();

        assertTrue(repository.findAll().isEmpty());
    }
}