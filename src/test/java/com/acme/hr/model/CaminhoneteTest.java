
package br.edu.locadora.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CaminhoneteTest {

    @Test
    void deveCriarCaminhoneteComDadosValidos() {

        Caminhonete caminhonete = new Caminhonete(
                "3",
                "DEF1B23",
                "Ford",
                "Ranger",
                2024,
                new BigDecimal("200.00"),
                1000
        );

        assertEquals("Ford", caminhonete.getMarca());
        assertEquals("Ranger", caminhonete.getModelo());
        assertEquals(1000, caminhonete.getCapacidadeCargaKg());
        assertTrue(caminhonete.isDisponivel());
    }

    @Test
    void deveCalcularAluguelComAcrescimoDe25PorCento() {

        Caminhonete caminhonete = new Caminhonete(
                "3",
                "DEF1B23",
                "Ford",
                "Ranger",
                2024,
                new BigDecimal("200.00"),
                1000
        );

        BigDecimal resultado =
                caminhonete.calcularValorAluguel(3);

        assertEquals(
                0,
                new BigDecimal("750.00").compareTo(resultado)
        );
    }

    @Test
    void naoDeveAceitarCapacidadeDeCargaZero() {

        assertThrows(IllegalArgumentException.class, () ->
                new Caminhonete(
                        "3",
                        "DEF1B23",
                        "Ford",
                        "Ranger",
                        2024,
                        new BigDecimal("200.00"),
                        0
                )
        );
    }

    @Test
    void naoDeveAceitarCapacidadeDeCargaNegativa() {

        assertThrows(IllegalArgumentException.class, () ->
                new Caminhonete(
                        "3",
                        "DEF1B23",
                        "Ford",
                        "Ranger",
                        2024,
                        new BigDecimal("200.00"),
                        -100
                )
        );
    }

    @Test
    void naoDeveAceitarQuantidadeDeDiasZero() {

        Caminhonete caminhonete = new Caminhonete(
                "3",
                "DEF1B23",
                "Ford",
                "Ranger",
                2024,
                new BigDecimal("200.00"),
                1000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> caminhonete.calcularValorAluguel(0)
        );
    }
}
