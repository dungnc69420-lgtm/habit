package com.habit.dto.request;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record HabitTemplateRequest(
        
        @NotBlank
        String name,
        
        String emoji,
        
        @NotBlank
        HabitType habitType,
        
        String color,
        
        @NotNull
        BigDecimal goalValue,
        
        @NotBlank
        String goalUnit,
        
        @NotBlank
        GoalPeriod goalPeriod

) {
}