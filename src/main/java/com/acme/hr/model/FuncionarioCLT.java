package com.acme.hr.model;

import com.acme.hr.model.Funcionario;

import java.math.BigDecimal;

public class FuncionarioCLT extends Funcionario {

    public FuncionarioCLT(
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

        return getSalarioBase()
                .multiply(BigDecimal.valueOf(dias));
    }

}
