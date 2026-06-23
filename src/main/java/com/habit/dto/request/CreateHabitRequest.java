package com.habit.dto.request;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record CreateHabitRequest(
        String name,
        String description,
        String groupName,
        HabitType habitType,
        String color,
        String emoji,
        
        BigDecimal goalValue,
        UUID goalUnitId,
        GoalPeriod goalPeriod,
        
        LocalTime startTime,
        LocalTime endTime,
        
        LocalDateTime startDate,
        LocalDateTime endDate,
        
        Boolean reminderEnabled,
        LocalTime reminderTime,
        
        CreateHabitScheduleRequest schedule
) {
}