package com.habit.dto.response;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitStatus;
import com.habit.enums.HabitType;
import com.habit.model.Habit;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record HabitResponse(
        UUID id,
        
        String name,
        String description,
        String groupName,
        
        HabitStatus status,
        String emoji,
        
        HabitType habitType,
        String color,
        
        BigDecimal goalValue,
        UUID goalUnitId,
        String goalUnitName,
        
        GoalPeriod goalPeriod,
        
        LocalTime startTime,
        LocalTime endTime,
        
        LocalDateTime startDate,
        LocalDateTime endDate,
        
        HabitScheduleResponse schedule,
        
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    
    public static HabitResponse mapToResponse(Habit habit) {
        
        HabitScheduleResponse scheduleResponse = null;
        
        if (habit.getSchedule() != null) {
            scheduleResponse =
                    new HabitScheduleResponse(
                            habit.getSchedule().getScheduleType(),
                            habit.getSchedule().getTargetDays(),
                            habit.getSchedule().getSelectedDays()
                    );
        }
        
        return new HabitResponse(
                habit.getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getGroupName(),
                habit.getStatus(),
                habit.getEmoji(),
                habit.getHabitType(),
                habit.getColor(),
                habit.getGoalValue(),
                habit.getGoalUnit().getId(),
                habit.getGoalUnit().getName(),
                habit.getGoalPeriod(),
                habit.getStartTime(),
                habit.getEndTime(),
                habit.getStartDate(),
                habit.getEndDate(),
                scheduleResponse,
                habit.getCreatedAt(),
                habit.getUpdatedAt()
        );
    }
}