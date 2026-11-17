package br.edu.locadora.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadosInvalidosExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {

        DadosInvalidosException excecao =
                new DadosInvalidosException(
                        "A quantidade de dias deve ser maior que zero."
                );

        assertEquals(
                "A quantidade de dias deve ser maior que zero.",
                excecao.getMessage()
        );
    }
}