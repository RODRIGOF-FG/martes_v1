package com.example.sistema_procesos.Admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AdminUserService.ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("not_found", ex.getMessage()));
    }

    @ExceptionHandler(AdminUserService.ConflictException.class)
    public ResponseEntity<?> conflict(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("conflict", ex.getMessage()));
    }

    @ExceptionHandler(AdminUserService.BadRequestException.class)
    public ResponseEntity<?> badRequest(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("bad_request", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArg(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("bad_request", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> other(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto("error", ex.getMessage()));
    }

    record ErrorDto(String error, String message) {}
}
