package com.picpay.rh.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroPadrao {

    private Integer status;

    private String mensagem;

    private Long timestamp;
}
