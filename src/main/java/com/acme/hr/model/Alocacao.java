package com.acme.hr.model;

import java.math.BigDecimal;

public class Alocacao {

    private String id;
    private Cliente cliente;
    private Funcionario funcionario;
    private int dias;
    private BigDecimal valorTotal;
    private boolean ativa;

    public Alocacao(
            String id,
            Cliente cliente,
            Funcionario funcionario,
            int dias) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "ID da locação inválido.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não informado.");
        }

        if (funcionario == null) {
            throw new IllegalArgumentException(
                    "Veículo não informado.");
        }

        if (dias <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade de dias deve ser maior que zero.");
        }

        if (!funcionario.isDisponivel()) {
            throw new IllegalArgumentException(
                    "O veículo não está disponível.");
        }

        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dias = dias;
        this.valorTotal = funcionario.calcularValorAluguel(dias);
        this.ativa = true;
    }

    public void finalizar() {

        if (!ativa) {
            throw new IllegalStateException(
                    "Esta locação já foi encerrada.");
        }

        ativa = false;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public int getDias() {
        return dias;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public boolean isAtiva() {
        return ativa;
    }
}
