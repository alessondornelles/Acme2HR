package com.acme.hr.model;

import com.acme.hr.model.Funcionario;

import java.math.BigDecimal;

public class FuncionarioCLT extends Funcionario {

    private int quantidadePortas;

    public FuncionarioCLT(
            String id,
            String placa,
            String marca,
            String modelo,
            int anoFabricacao,
            BigDecimal valorDiaria,
            int quantidadePortas) {

        super(
                id,
                placa,
                marca,
                modelo,
                anoFabricacao,
                valorDiaria
        );

        if (quantidadePortas <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de portas deve ser maior que zero."
            );
        }

        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public BigDecimal calcularValorAluguel(int dias) {

        validarDias(dias);

        return getValorDiaria()
                .multiply(BigDecimal.valueOf(dias));
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }
}
