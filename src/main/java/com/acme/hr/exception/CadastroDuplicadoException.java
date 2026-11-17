package com.acme.hr.exception;

public class CadastroDuplicadoException extends RuntimeException {

    public CadastroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}