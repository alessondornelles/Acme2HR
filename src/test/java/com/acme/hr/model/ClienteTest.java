
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComDadosValidos() {

        Cliente cliente = new Cliente(
                "1",
                "João da Silva",
                "123.456.789-09",
                "joao@email.com",
                "(48) 99999-9999"
        );

        assertEquals("1", cliente.getId());
        assertEquals("João da Silva", cliente.getNome());
        assertEquals("123.456.789-09", cliente.getCpf());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("(48) 99999-9999", cliente.getTelefone());
    }

    @Test
    void naoDeveAceitarNomeVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(
                        "1",
                        "",
                        "123.456.789-09",
                        "joao@email.com",
                        "(48) 99999-9999"
                )
        );
    }

    @Test
    void naoDeveAceitarCpfComFormatoInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(
                        "1",
                        "João da Silva",
                        "12345",
                        "joao@email.com",
                        "(48) 99999-9999"
                )
        );
    }

    @Test
    void naoDeveAceitarCpfComLetras() {

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(
                        "1",
                        "João da Silva",
                        "123.456.ABC-09",
                        "joao@email.com",
                        "(48) 99999-9999"
                )
        );
    }

    @Test
    void naoDeveAceitarIdVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(
                        "",
                        "João da Silva",
                        "123.456.789-09",
                        "joao@email.com",
                        "(48) 99999-9999"
                )
        );
    }
}
