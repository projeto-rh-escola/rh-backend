package com.picpay.rh.exception;

import com.picpay.rh.handler.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class DadoDuplicadoException extends RuntimeException {

    private final List<FieldError> erros;

    public DadoDuplicadoException(List<FieldError> erros) {
        super("Dados duplicados: " + erros.stream().map(FieldError::message).collect(Collectors.joining(" | ")));
        this.erros = erros;
    }

    public List<FieldError> getErros() {
        return erros;
    }
}
