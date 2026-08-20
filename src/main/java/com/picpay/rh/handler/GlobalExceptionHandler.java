package com.picpay.rh.handler;

import com.picpay.rh.exception.DadoDuplicadoException;
import com.picpay.rh.exception.FuncionarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DadoDuplicadoException.class)
    public ResponseEntity<ErroPadrao> handleDadoDuplicado(DadoDuplicadoException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setStatus(HttpStatus.CONFLICT.value());
        erro.setMensagem(ex.getMessage());
        erro.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> handleErrosDeValidacao(MethodArgumentNotValidException ex) {
        String mensagemDeErro = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ErroPadrao erro = new ErroPadrao();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setMensagem(mensagemDeErro);
        erro.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> handleFuncionarioNaoEncontrado(FuncionarioNaoEncontradoException ex) {
        ErroPadrao erro = new ErroPadrao();
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setMensagem(ex.getMessage());
        erro.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
