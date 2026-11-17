package br.edu.locadora.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoIndisponivelExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {

        VeiculoIndisponivelException excecao =
                new VeiculoIndisponivelException(
                        "O veículo não está disponível."
                );

        assertEquals(
                "O veículo não está disponível.",
                excecao.getMessage()
        );
    }
}