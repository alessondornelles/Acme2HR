
package br.edu.locadora.service;
import br.edu.locadora.domain.Locacao;
import br.edu.locadora.domain.Carro;
import br.edu.locadora.domain.Cliente;
import br.edu.locadora.repository.ClienteRepositoryMemoria;
import br.edu.locadora.repository.LocacaoRepositoryMemoria;
import br.edu.locadora.repository.VeiculoRepositoryMemoria;
import org.junit.jupiter.api.Test;
import br.edu.locadora.exception.EntidadeNaoEncontradaException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LocacaoServiceTest {

    @Test
    void deveCriarLocacao() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        var locacao = service.criarLocacao(
                "L01",
                "C01",
                "V01",
                5
        );

        assertNotNull(locacao);
        assertEquals("L01", locacao.getId());
        assertEquals(new BigDecimal("750.00"), locacao.getValorTotal());
        assertFalse(carro.isDisponivel());
        assertTrue(locacao.isAtiva());
    }

    @Test
    void naoDeveAlugarVeiculoIndisponivel() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.criarLocacao("L02", "C01", "V01", 3)
        );
    }

    @Test
    void naoDeveCriarLocacaoComDiasInvalidos() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.criarLocacao("L01", "C01", "V01", 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.criarLocacao("L02", "C01", "V01", -5)
        );
    }

    @Test
    void naoDeveCriarLocacaoComClienteInexistente() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.criarLocacao("L01", "C99", "V01", 5)
        );

        assertTrue(carro.isDisponivel());
    }

    @Test
    void naoDeveCriarLocacaoComVeiculoInexistente() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        clienteRepository.save(cliente);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.criarLocacao("L01", "C01", "V99", 5)
        );

        assertTrue(locacaoRepository.findAll().isEmpty());
    }

    @Test
    void deveBuscarLocacaoPorId() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);

        var encontrada = service.buscarPorId("L01");

        assertNotNull(encontrada);
        assertEquals("L01", encontrada.getId());
        assertEquals("João Silva", encontrada.getCliente().getNome());
        assertEquals("Corolla", encontrada.getVeiculo().getModelo());
    }

    @Test
    void naoDeveBuscarLocacaoInexistente() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId("L99")
        );
    }

    @Test
    void deveListarTodasLocacoes() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro1 = new Carro(
                "V01", "ABC-1234", "Toyota", "Corolla",
                2023, new BigDecimal("150.00"), 4
        );

        Carro carro2 = new Carro(
                "V02", "DEF-5678", "Honda", "Civic",
                2022, new BigDecimal("180.00"), 4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro1);
        veiculoRepository.save(carro2);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);
        service.criarLocacao("L02", "C01", "V02", 3);

        var locacoes = service.listarTodas();

        assertEquals(2, locacoes.size());
    }

    @Test
    void deveFinalizarLocacaoEDevolverVeiculo() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);

        assertFalse(carro.isDisponivel());

        service.devolverLocacao("L01");

        Locacao locacao = service.buscarPorId("L01");

        assertTrue(carro.isDisponivel());
        assertFalse(locacao.isAtiva());
    }

    @Test
    void naoDeveDevolverLocacaoDuasVezes() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro = new Carro(
                "V01",
                "ABC-1234",
                "Toyota",
                "Corolla",
                2023,
                new BigDecimal("150.00"),
                4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);

        service.devolverLocacao("L01");

        assertThrows(
                IllegalStateException.class,
                () -> service.devolverLocacao("L01")
        );
    }

    @Test
    void deveListarSomenteLocacoesAtivas() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro1 = new Carro(
                "V01", "ABC-1234", "Toyota", "Corolla",
                2023, new BigDecimal("150.00"), 4
        );

        Carro carro2 = new Carro(
                "V02", "DEF-5678", "Honda", "Civic",
                2022, new BigDecimal("180.00"), 4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro1);
        veiculoRepository.save(carro2);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);
        service.criarLocacao("L02", "C01", "V02", 3);

        service.devolverLocacao("L01");

        var locacoesAtivas = service.listarAtivas();

        assertEquals(1, locacoesAtivas.size());
        assertEquals("L02", locacoesAtivas.get(0).getId());
    }

    @Test
    void deveListarHistoricoDeLocacoes() {

        ClienteRepositoryMemoria clienteRepository =
                new ClienteRepositoryMemoria();

        VeiculoRepositoryMemoria veiculoRepository =
                new VeiculoRepositoryMemoria();

        LocacaoRepositoryMemoria locacaoRepository =
                new LocacaoRepositoryMemoria();

        Cliente cliente = new Cliente(
                "C01",
                "João Silva",
                "123.456.789-00",
                "joao@email.com",
                "48999999999"
        );

        Carro carro1 = new Carro(
                "V01", "ABC-1234", "Toyota", "Corolla",
                2023, new BigDecimal("150.00"), 4
        );

        Carro carro2 = new Carro(
                "V02", "DEF-5678", "Honda", "Civic",
                2022, new BigDecimal("180.00"), 4
        );

        clienteRepository.save(cliente);
        veiculoRepository.save(carro1);
        veiculoRepository.save(carro2);

        LocacaoService service = new LocacaoService(
                clienteRepository,
                veiculoRepository,
                locacaoRepository
        );

        service.criarLocacao("L01", "C01", "V01", 5);
        service.criarLocacao("L02", "C01", "V02", 3);

        service.devolverLocacao("L01");

        var historico = service.listarTodas();

        assertEquals(2, historico.size());
    }
}
