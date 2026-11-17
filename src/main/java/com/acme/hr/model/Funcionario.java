package com.acme.hr.model;

import java.math.BigDecimal;
import java.time.Year;

public abstract class Funcionario {

    private String id;
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private BigDecimal valorDiaria;
    private boolean disponivel;

    public Funcionario(
            String id,
            String placa,
            String marca,
            String modelo,
            int anoFabricacao,
            BigDecimal valorDiaria) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }

        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa inválida.");
        }

        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Marca inválida.");
        }

        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo inválido.");
        }

        if (anoFabricacao < 1886 ||
                anoFabricacao > Year.now().getValue()) {
            throw new IllegalArgumentException(
                    "Ano de fabricação inválido.");
        }

        if (valorDiaria == null ||
                valorDiaria.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor da diária deve ser maior que zero.");
        }

        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    public abstract BigDecimal calcularValorAluguel(int dias);

    protected void validarDias(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de dias deve ser maior que zero.");
        }
    }

    public String getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void alugar() {
        if (!disponivel) {
            throw new IllegalStateException(
                    "O veículo já está alugado.");
        }

        disponivel = false;
    }

    public void devolver() {
        if (disponivel) {
            throw new IllegalStateException(
                    "O veículo já está disponível.");
        }

        disponivel = true;
    }
}
