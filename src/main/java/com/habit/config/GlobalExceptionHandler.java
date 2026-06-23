package com.habit.config;

import com.habit.dto.common.ErrorResponse;
import com.habit.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex
    ) {
        
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code("BAD_REQUEST")
                        .message(ex.getMessage())
                        .build()
                );
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException ex
    ) {
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.builder()
                        .code("UNAUTHORIZED")
                        .message("Invalid credentials")
                        .build()
                );
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .code("VALIDATION_ERROR")
                        .message(message)
                        .build()
                );
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(
            Exception ex
    ) {
        
        log.error("Unexpected error", ex);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .message("Something went wrong. Please try again later.")
                        .build()
                );
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseError(
            DataIntegrityViolationException ex
    ) {
        
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .code("DATABASE_ERROR")
                        .message("Invalid data supplied")
                        .build()
                );
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException e
    ) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode()
                                .getStatus().name()
                        )
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build()
                );
    }
}
