
package br.edu.locadora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitConfiguracaoTest {

    @Test
    void verificarConfiguracaoJUnit() {

        int resultado = 2 + 2;

        assertEquals(4, resultado);
    }
}
