package com.acme.hr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuncionarioComissionado extends Funcionario {

    private int capacidadeCargaKg;

    public FuncionarioComissionado(
            String id,
            String placa,
            String marca,
            String modelo,
            int anoFabricacao,
            BigDecimal valorDiaria,
            int capacidadeCargaKg) {

        super(
                id,
                placa,
                marca,
                modelo,
                anoFabricacao,
                valorDiaria
        );

        if (capacidadeCargaKg <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade de carga deve ser maior que zero."
            );
        }

        this.capacidadeCargaKg = capacidadeCargaKg;
    }

    @Override
    public BigDecimal calcularValorAluguel(int dias) {

        validarDias(dias);

        BigDecimal valorNormal = getValorDiaria()
                .multiply(BigDecimal.valueOf(dias));

        BigDecimal acrescimo = new BigDecimal("0.25");

        return valorNormal
                .multiply(BigDecimal.ONE.add(acrescimo))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int getCapacidadeCargaKg() {
        return capacidadeCargaKg;
    }
}
