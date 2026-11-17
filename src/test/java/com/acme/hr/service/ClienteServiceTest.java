package br.edu.locadora.service;

import br.edu.locadora.domain.Cliente;
import br.edu.locadora.repository.ClienteRepositoryMemoria;
import br.edu.locadora.exception.CadastroDuplicadoException;
import br.edu.locadora.exception.EntidadeNaoEncontradaException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    @Test
    void deveCadastrarCliente() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        service.cadastrar(cliente);

        assertTrue(repository.findById("C01").isPresent());
    }

    @Test
    void naoDeveCadastrarClienteComCpfDuplicado() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        Cliente cliente1 = new Cliente(
                "C01", "João Silva",
                "123.456.789-00",
                "joao@email.com", "48999999999"
        );

        Cliente cliente2 = new Cliente(
                "C02", "Maria Souza",
                "123.456.789-00",
                "maria@email.com", "48988888888"
        );

        service.cadastrar(cliente1);

        assertThrows(
                CadastroDuplicadoException.class,
                () -> service.cadastrar(cliente2)
        );
    }
    @Test
    void deveListarTodosClientes() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        Cliente cliente1 = new Cliente(
                "C01", "João Silva",
                "123.456.789-00",
                "joao@email.com", "48999999999"
        );

        Cliente cliente2 = new Cliente(
                "C02", "Maria Souza",
                "987.654.321-00",
                "maria@email.com", "48988888888"
        );

        service.cadastrar(cliente1);
        service.cadastrar(cliente2);

        assertEquals(2, service.listarTodos().size());
    }
    @Test
    void deveBuscarClientePorId() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        service.cadastrar(cliente);

        Cliente encontrado = service.buscarPorId("C01");

        assertNotNull(encontrado);
        assertEquals("João Silva", encontrado.getNome());
        assertEquals("123.456.789-00", encontrado.getCpf());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId("C909")
        );
    }

    @Test
    void deveRemoverClienteCadastrado() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        service.cadastrar(cliente);

        service.remover("C01");

        assertTrue(repository.findById("C01").isEmpty());
    }

    @Test
    void naoDeveRemoverClienteInexistente() {

        ClienteRepositoryMemoria repository =
                new ClienteRepositoryMemoria();

        ClienteService service = new ClienteService(repository);

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.remover("C99")
        );
    }
}
