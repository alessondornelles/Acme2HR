
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MotoTest {

    @Test
    void deveCriarMotoComDadosValidos() {

        Moto moto = new Moto(
                "2",
                "XYZ1A23",
                "Honda",
                "CB 500",
                2024,
                new BigDecimal("100.00"),
                500
        );

        assertEquals("Honda", moto.getMarca());
        assertEquals("CB 500", moto.getModelo());
        assertEquals(500, moto.getCilindradas());
        assertTrue(moto.isDisponivel());
    }

    @Test
    void deveCalcularAluguelComDescontoDe15PorCento() {

        Moto moto = new Moto(
                "2",
                "XYZ1A23",
                "Honda",
                "CB 500",
                2024,
                new BigDecimal("100.00"),
                500
        );

        BigDecimal resultado = moto.calcularValorAluguel(3);

        assertEquals(
                0,
                new BigDecimal("255.00").compareTo(resultado)
        );
    }

    @Test
    void naoDeveAceitarCilindradasZero() {

        assertThrows(IllegalArgumentException.class, () ->
                new Moto(
                        "2",
                        "XYZ1A23",
                        "Honda",
                        "CB 500",
                        2024,
                        new BigDecimal("100.00"),
                        0
                )
        );
    }

    @Test
    void naoDeveAceitarCilindradasNegativas() {

        assertThrows(IllegalArgumentException.class, () ->
                new Moto(
                        "2",
                        "XYZ1A23",
                        "Honda",
                        "CB 500",
                        2024,
                        new BigDecimal("100.00"),
                        -500
                )
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDeDiasZero() {

        Moto moto = new Moto(
                "2",
                "XYZ1A23",
                "Honda",
                "CB 500",
                2024,
                new BigDecimal("100.00"),
                500
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> moto.calcularValorAluguel(0)
        );
    }
}
