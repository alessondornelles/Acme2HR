package br.edu.locadora.service;

import br.edu.locadora.domain.Moto;
import br.edu.locadora.domain.Carro;
import br.edu.locadora.domain.Veiculo;
import br.edu.locadora.repository.VeiculoRepositoryMemoria;
import br.edu.locadora.exception.CadastroDuplicadoException;
import br.edu.locadora.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoServiceTest {

    @Test
    void deveCadastrarVeiculo() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01",
                "ABC-1234",
                "Fiat",
                "Argo",
                2022,
                new BigDecimal("150.00"),
                4
        );

        service.cadastrar(carro);

        assertTrue(repository.findById("V01").isPresent());
    }

    @Test
    void naoDeveCadastrarVeiculoComPlacaDuplicada() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro1 = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        Veiculo carro2 = new Carro(
                "V02", "ABC-1234", "Volkswagen", "Polo",
                2023, new BigDecimal("180.00"), 4
        );

        service.cadastrar(carro1);

        assertThrows(
                CadastroDuplicadoException.class,
                () -> service.cadastrar(carro2)
        );
    }

    @Test
    void deveListarTodosVeiculos() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        Veiculo moto = new Moto(
                "V02", "XYZ-5678", "Honda", "CG 160",
                2023, new BigDecimal("80.00"), 160
        );

        service.cadastrar(carro);
        service.cadastrar(moto);

        assertEquals(2, service.listarTodos().size());
    }
    @Test
    void deveListarSomenteVeiculosDisponiveis() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        Veiculo moto = new Moto(
                "V02", "XYZ-5678", "Honda", "CG 160",
                2023, new BigDecimal("80.00"), 160
        );

        // Simula um veículo que já está alugado.
        carro.alugar();

        service.cadastrar(carro);
        service.cadastrar(moto);

        assertEquals(1, service.listarDisponiveis().size());

        assertEquals(
                "V02",
                service.listarDisponiveis().get(0).getId()
        );
    }
    @Test
    void deveBuscarVeiculoPorId() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        service.cadastrar(carro);

        Veiculo encontrado = service.buscarPorId("V01");

        assertNotNull(encontrado);
        assertEquals("Fiat", encontrado.getMarca());
        assertEquals("Argo", encontrado.getModelo());
    }
    @Test
    void deveLancarExcecaoQuandoVeiculoNaoExistir() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId("V99")
        );
    }

    @Test
    void deveRemoverVeiculoCadastrado() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        service.cadastrar(carro);

        service.remover("V01");

        assertTrue(repository.findById("V01").isEmpty());
    }

    @Test
    void naoDeveRemoverVeiculoAlugado() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        VeiculoService service = new VeiculoService(repository);

        Veiculo carro = new Carro(
                "V01", "ABC-1234", "Fiat", "Argo",
                2022, new BigDecimal("150.00"), 4
        );

        service.cadastrar(carro);

        carro.alugar();

        assertThrows(
                IllegalStateException.class,
                () -> service.remover("V01")
        );

        assertTrue(repository.findById("V01").isPresent());
    }
}
