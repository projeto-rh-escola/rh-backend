package com.picpay.rh.handler;

import com.picpay.rh.exception.DadoDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DadoDuplicadoException.class)
    public ResponseEntity<ErroPadrao> handleDadoDuplicado(DadoDuplicadoException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setStatus(HttpStatus.CONFLICT.value());
        erro.setMensagem(ex.getMessage());
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> handleErrosDeValidacao(MethodArgumentNotValidException ex) {
        String mensagemDeErro = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ErroPadrao erro = new ErroPadrao();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(mensagemDeErro);
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
