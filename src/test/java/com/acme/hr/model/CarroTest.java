
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarroTest {

    @Test
    void deveCriarCarroComDadosValidos() {

        Carro carro = new Carro(
                "1",
                "ABC1D23",
                "Toyota",
                "Corolla",
                2024,
                new BigDecimal("150.00"),
                4
        );

        assertEquals("Toyota", carro.getMarca());
        assertEquals("Corolla", carro.getModelo());
        assertEquals(4, carro.getQuantidadePortas());
        assertTrue(carro.isDisponivel());
    }

    @Test
    void deveCalcularValorDoAluguel() {

        Carro carro = new Carro(
                "1",
                "ABC1D23",
                "Toyota",
                "Corolla",
                2024,
                new BigDecimal("150.00"),
                4
        );

        BigDecimal resultado = carro.calcularValorAluguel(3);

        assertEquals(
                0,
                new BigDecimal("450.00").compareTo(resultado)
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDePortasZero() {

        assertThrows(IllegalArgumentException.class, () ->
                new Carro(
                        "1",
                        "ABC1D23",
                        "Toyota",
                        "Corolla",
                        2024,
                        new BigDecimal("150.00"),
                        0
                )
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDePortasNegativa() {

        assertThrows(IllegalArgumentException.class, () ->
                new Carro(
                        "1",
                        "ABC1D23",
                        "Toyota",
                        "Corolla",
                        2024,
                        new BigDecimal("150.00"),
                        -2
                )
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDeDiasZero() {

        Carro carro = new Carro(
                "1",
                "ABC1D23",
                "Toyota",
                "Corolla",
                2024,
                new BigDecimal("150.00"),
                4
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> carro.calcularValorAluguel(0)
        );
    }
}
