
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LocacaoTest {

    private Cliente criarCliente() {
        return new Cliente(
                "1",
                "João da Silva",
                "123.456.789-09",
                "joao@email.com",
                "(48) 99999-9999"
        );
    }

    private Carro criarCarro() {
        return new Carro(
                "1",
                "ABC1D23",
                "Toyota",
                "Corolla",
                2024,
                new BigDecimal("150.00"),
                4
        );
    }

    @Test
    void deveCriarLocacaoComDadosValidos() {

        Locacao locacao = new Locacao(
                "1",
                criarCliente(),
                criarCarro(),
                3
        );

        assertEquals("1", locacao.getId());
        assertEquals("João da Silva", locacao.getCliente().getNome());
        assertEquals("Corolla", locacao.getVeiculo().getModelo());
        assertEquals(3, locacao.getDias());
        assertTrue(locacao.isAtiva());
    }

    @Test
    void deveCalcularValorTotalDaLocacao() {

        Locacao locacao = new Locacao(
                "1",
                criarCliente(),
                criarCarro(),
                3
        );

        assertEquals(
                0,
                new BigDecimal("450.00")
                        .compareTo(locacao.getValorTotal())
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDeDiasZero() {

        assertThrows(IllegalArgumentException.class, () ->
                new Locacao(
                        "1",
                        criarCliente(),
                        criarCarro(),
                        0
                )
        );
    }

    @Test
    void naoDeveAceitarClienteNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                new Locacao(
                        "1",
                        null,
                        criarCarro(),
                        3
                )
        );
    }

    @Test
    void deveEncerrarLocacao() {

        Locacao locacao = new Locacao(
                "1",
                criarCliente(),
                criarCarro(),
                3
        );

        locacao.finalizar();

        assertFalse(locacao.isAtiva());
    }
}
