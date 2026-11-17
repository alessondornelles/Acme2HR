package com.acme.hr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioPJ extends Funcionario {

    public FuncionarioPJ(
            String id,
            String nome,
            String cpf,
            BigDecimal salarioBase) {

        super(
                id,
                nome,
                cpf,
                salarioBase
        );

    }

    @Override
    public BigDecimal calcularValorAluguel(int dias) {

        validarDias(dias);

        BigDecimal valorNormal = getSalarioBase()
                .multiply(BigDecimal.valueOf(dias));

        BigDecimal desconto = new BigDecimal("0.15");

        return valorNormal
                .multiply(BigDecimal.ONE.subtract(desconto))
                .setScale(2, RoundingMode.HALF_UP);
    }

}
