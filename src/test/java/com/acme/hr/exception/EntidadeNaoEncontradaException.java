package br.edu.locadora.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntidadeNaoEncontradaExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {

        EntidadeNaoEncontradaException excecao =
                new EntidadeNaoEncontradaException(
                        "Cliente não encontrado."
                );

        assertEquals(
                "Cliente não encontrado.",
                excecao.getMessage()
        );
    }
}