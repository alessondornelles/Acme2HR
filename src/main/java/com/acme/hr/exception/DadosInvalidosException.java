package com.acme.hr.exception;

public class DadosInvalidosException extends RuntimeException {

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}