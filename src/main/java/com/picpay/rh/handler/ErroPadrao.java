package com.picpay.rh.handler;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ErroPadrao {

    private Integer status;

    private String mensagem;

    private OffsetDateTime timestamp;
}
