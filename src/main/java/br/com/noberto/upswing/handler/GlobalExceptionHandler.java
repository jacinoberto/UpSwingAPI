package br.com.noberto.upswing.handler;

import br.com.noberto.upswing.handler.exception.ResponseErrorException;
import jakarta.persistence.EntityExistsException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ResponseErrorException> emailException(EntityExistsException ex){
        ResponseErrorException error = new ResponseErrorException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<ResponseErrorException> emailException(TransientPropertyValueException ex){
        ResponseErrorException error = new ResponseErrorException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
