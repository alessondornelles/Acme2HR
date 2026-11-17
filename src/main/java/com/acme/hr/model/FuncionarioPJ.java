package com.acme.hr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioPJ extends Funcionario {

    private int cilindradas;

    public FuncionarioPJ(
            String id,
            String placa,
            String marca,
            String modelo,
            int anoFabricacao,
            BigDecimal valorDiaria,
            int cilindradas) {

        super(
                id,
                placa,
                marca,
                modelo,
                anoFabricacao,
                valorDiaria
        );

        if (cilindradas <= 0) {
            throw new IllegalArgumentException(
                    "As cilindradas devem ser maiores que zero."
            );
        }

        this.cilindradas = cilindradas;
    }

    @Override
    public BigDecimal calcularValorAluguel(int dias) {

        validarDias(dias);

        BigDecimal valorNormal = getValorDiaria()
                .multiply(BigDecimal.valueOf(dias));

        BigDecimal desconto = new BigDecimal("0.15");

        return valorNormal
                .multiply(BigDecimal.ONE.subtract(desconto))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int getCilindradas() {
        return cilindradas;
    }
}
