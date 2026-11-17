package com.acme.hr.model;

import java.math.BigDecimal;
import java.time.Year;

public abstract class Funcionario {

    private String id;
    private String nome;
    private String cpf;
    private BigDecimal salarioBase;
    private boolean disponivel;

    public Funcionario(
            String id,
            String nome,
            String cpf,
            BigDecimal salarioBase
    ) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        if (cpf == null ||
                !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException(
                    "CPF inválido. Utilize o formato 000.000.000-00.");
        }

        if (salarioBase == null ||
                salarioBase.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O salário base deve ser maior que zero.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
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

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void alugar() {
        if (!disponivel) {
            throw new IllegalStateException(
                    "O funcionário já está alocado.");
        }

        disponivel = false;
    }

    public void devolver() {
        if (disponivel) {
            throw new IllegalStateException(
                    "O funcionário já está disponível.");
        }

        disponivel = true;
    }
}
