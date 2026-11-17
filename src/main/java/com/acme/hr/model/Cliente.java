package com.acme.hr.model;

public class Cliente {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente(
            String id,
            String nome,
            String cpf,
            String email,
            String telefone) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "ID do cliente inválido.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do cliente inválido.");
        }

        if (cpf == null ||
                !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException(
                    "CPF inválido. Utilize o formato 000.000.000-00.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "E-mail do cliente inválido.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException(
                    "Telefone do cliente inválido.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
