
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoTest {

    // Classe auxiliar para testar a classe abstrata Veiculo
    private static class VeiculoTeste extends Veiculo {

        public VeiculoTeste(
                String id,
                String placa,
                String marca,
                String modelo,
                int anoFabricacao,
                BigDecimal valorDiaria) {

            super(id, placa, marca, modelo,
                    anoFabricacao, valorDiaria);
        }

        @Override
        public BigDecimal calcularValorAluguel(int dias) {

            return getValorDiaria()
                    .multiply(BigDecimal.valueOf(dias));
        }
    }

    @Test
    void deveCriarVeiculoComDadosValidos() {

        Veiculo veiculo = new VeiculoTeste(
                "1",
                "ABC1D23",
                "Honda",
                "Civic",
                2024,
                new BigDecimal("150.00")
        );

        assertEquals("1", veiculo.getId());
        assertEquals("ABC1D23", veiculo.getPlaca());
        assertEquals("Honda", veiculo.getMarca());
        assertEquals("Civic", veiculo.getModelo());
        assertEquals(2024, veiculo.getAnoFabricacao());
        assertEquals(
                0,
                new BigDecimal("150.00")
                        .compareTo(veiculo.getValorDiaria())
        );

        assertTrue(veiculo.isDisponivel());
    }

    @Test
    void naoDeveAceitarDiariaZero() {

        assertThrows(IllegalArgumentException.class, () ->
                new VeiculoTeste(
                        "1", "ABC1D23", "Honda", "Civic",
                        2024, BigDecimal.ZERO
                )
        );
    }

    @Test
    void naoDeveAceitarDiariaNegativa() {

        assertThrows(IllegalArgumentException.class, () ->
                new VeiculoTeste(
                        "1", "ABC1D23", "Honda", "Civic",
                        2024, new BigDecimal("-100.00")
                )
        );
    }

    @Test
    void naoDeveAceitarAnoFuturo() {

        assertThrows(IllegalArgumentException.class, () ->
                new VeiculoTeste(
                        "1", "ABC1D23", "Honda", "Civic",
                        2030, new BigDecimal("150.00")
                )
        );
    }

    @Test
    void naoDeveAceitarMarcaVazia() {

        assertThrows(IllegalArgumentException.class, () ->
                new VeiculoTeste(
                        "1", "ABC1D23", "", "Civic",
                        2024, new BigDecimal("150.00")
                )
        );
    }
}
