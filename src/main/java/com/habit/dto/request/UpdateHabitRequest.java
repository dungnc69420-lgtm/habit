package com.habit.dto.request;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record UpdateHabitRequest(
        String name,
        String description,
        String groupName,
        HabitType habitType,
        String color,
        
        BigDecimal goalValue,
        UUID goalUnitId,
        GoalPeriod goalPeriod,
        
        LocalTime startTime,
        LocalTime endTime,
        
        LocalDateTime startDate,
        LocalDateTime endDate,
        
        Boolean reminderEnabled,
        LocalTime reminderTime,
        
        UpdateHabitScheduleRequest schedule
) {
}