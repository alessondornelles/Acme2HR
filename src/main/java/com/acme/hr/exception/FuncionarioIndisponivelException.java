package com.acme.hr.exception;

public class FuncionarioIndisponivelException extends RuntimeException {

    public FuncionarioIndisponivelException(String mensagem) {
        super(mensagem);
    }
}