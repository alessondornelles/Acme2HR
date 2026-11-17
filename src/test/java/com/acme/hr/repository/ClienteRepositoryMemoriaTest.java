
package br.edu.locadora.repository;

import br.edu.locadora.domain.Cliente;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryMemoriaTest {

    private Cliente criarCliente() {
        return new Cliente(
                "1",
                "João da Silva",
                "123.456.789-09",
                "joao@email.com",
                "(48) 99999-9999"
        );
    }

    @Test
    void deveSalvarCliente() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        repository.save(criarCliente());

        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void deveBuscarClientePorId() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        repository.save(criarCliente());

        Optional<Cliente> resultado =
                repository.findById("1");

        assertTrue(resultado.isPresent());
        assertEquals("João da Silva", resultado.get().getNome());
    }

    @Test
    void deveRetornarVazioQuandoClienteNaoExistir() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        Optional<Cliente> resultado =
                repository.findById("99");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveListarTodosOsClientes() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        repository.save(criarCliente());

        List<Cliente> resultado = repository.findAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveExcluirClientePorId() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        repository.save(criarCliente());

        repository.deleteById("1");

        assertTrue(repository.findById("1").isEmpty());
    }
}
