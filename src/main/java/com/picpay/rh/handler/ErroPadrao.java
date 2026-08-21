package com.picpay.rh.handler;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class ErroPadrao {

    private Integer status;

    private String mensagem;

    private List<FieldError> erros;

    private OffsetDateTime timestamp;
}
