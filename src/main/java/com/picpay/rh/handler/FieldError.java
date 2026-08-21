package com.picpay.rh.handler;

public record FieldError(
        String field,
        String message
) {
}
