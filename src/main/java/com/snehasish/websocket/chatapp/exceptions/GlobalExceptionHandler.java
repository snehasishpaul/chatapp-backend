package com.snehasish.websocket.chatapp.exceptions;

import com.snehasish.websocket.chatapp.payload.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleAllException(Exception ex, WebRequest request) {
        Response<?> errorResponse = Response.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Response<?> errorResponse = Response.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
