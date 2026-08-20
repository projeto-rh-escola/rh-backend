package com.picpay.rh.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {

    public FuncionarioNaoEncontradoException(Long id) {
        super(String.format("Funcionario with id %d not found", id));
    }
}
