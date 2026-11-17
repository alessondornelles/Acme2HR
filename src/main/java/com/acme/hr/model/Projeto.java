package com.acme.hr.model;

public class Projeto {

    private String id;
    private String nome;

    public Projeto(
            String id,
            String nome) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "ID do projeto inválido.");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "Nome do projeto inválido.");
        }

        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
