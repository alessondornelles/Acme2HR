package com.acme.hr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioComissionado extends Funcionario {

    public FuncionarioComissionado(
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

        BigDecimal acrescimo = new BigDecimal("0.25");

        return valorNormal
                .multiply(BigDecimal.ONE.add(acrescimo))
                .setScale(2, RoundingMode.HALF_UP);
    }

}
