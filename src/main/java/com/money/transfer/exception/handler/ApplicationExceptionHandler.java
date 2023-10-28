package com.money.transfer.exception.handler;

import com.money.transfer.exception.ForbiddenException;
import com.money.transfer.exception.InsufficientFundsException;
import com.money.transfer.exception.ResourceConflictException;
import com.money.transfer.exception.ResourceNotFoundException;
import com.money.transfer.exception.ResourceViolationException;
import com.money.transfer.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private ErrorDetails buildResponseError(String message) {
        return ErrorDetails.builder()
                .message(message)
                .build();
    }

    @ExceptionHandler(ResourceViolationException.class)
    public ResponseEntity<ErrorDetails> resourceViolationExceptionHandler(ResourceViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponseError(ex.getMessage()));
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorDetails> resourceConflictExceptionHandler(ResourceConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildResponseError(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildResponseError(ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponseError("Invalid request data"));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorDetails> insufficientFundsExceptionHandler(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(buildResponseError(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDetails> unauthorizedExceptionHandler(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(buildResponseError(ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDetails> forbiddenExceptionHandler(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(buildResponseError(ex.getMessage()));
    }
}
