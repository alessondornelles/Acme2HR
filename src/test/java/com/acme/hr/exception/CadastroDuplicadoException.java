package br.edu.locadora.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastroDuplicadoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {

        CadastroDuplicadoException excecao =
                new CadastroDuplicadoException(
                        "Já existe um cadastro com esse CPF."
                );

        assertEquals(
                "Já existe um cadastro com esse CPF.",
                excecao.getMessage()
        );
    }
}