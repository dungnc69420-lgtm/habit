package com.habit.exceptions;

import com.habit.enums.ErrorCode;

public final class Exceptions {
    
    private Exceptions() {
    }
    
    public static BusinessException notFound(
            String resource,
            Object id
    ) {
        return new BusinessException(
                ErrorCode.RESOURCE_NOT_FOUND,
                resource + " not found with id: " + id
        );
    }
}