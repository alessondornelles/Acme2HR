
package br.edu.locadora.repository;

import br.edu.locadora.domain.Carro;
import br.edu.locadora.domain.Veiculo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoRepositoryMemoriaTest {

    private Veiculo criarCarro() {
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
    void deveSalvarVeiculo() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        Veiculo veiculo = criarCarro();

        repository.save(veiculo);

        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void deveBuscarVeiculoPorId() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        Veiculo veiculo = criarCarro();

        repository.save(veiculo);

        Optional<Veiculo> resultado =
                repository.findById("1");

        assertTrue(resultado.isPresent());
        assertEquals("Corolla", resultado.get().getModelo());
    }

    @Test
    void deveRetornarVazioQuandoVeiculoNaoExistir() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        Optional<Veiculo> resultado =
                repository.findById("99");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveListarTodosOsVeiculos() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        repository.save(criarCarro());

        List<Veiculo> resultado = repository.findAll();

        assertEquals(1, resultado.size());
    }

    @Test
    void deveExcluirVeiculoPorId() {

        VeiculoRepositoryMemoria repository =
                new VeiculoRepositoryMemoria();

        repository.save(criarCarro());

        repository.deleteById("1");

        assertTrue(repository.findById("1").isEmpty());
    }
}
