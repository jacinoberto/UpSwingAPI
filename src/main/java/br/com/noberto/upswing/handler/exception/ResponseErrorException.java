package br.com.noberto.upswing.handler.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseErrorException(
        String message,
        HttpStatus status,
        LocalDateTime dateTime
) {
}
