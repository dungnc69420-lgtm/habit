package com.habit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    RESOURCE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "Resource not found"
    ),
    
    INVALID_REQUEST(
            HttpStatus.BAD_REQUEST,
            "Invalid request"
    ),
    
    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal server error"
    );
    
    private final HttpStatus status;
    private final String message;
}