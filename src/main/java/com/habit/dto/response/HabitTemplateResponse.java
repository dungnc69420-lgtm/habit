package com.habit.dto.response;

import com.habit.enums.HabitType;
import com.habit.model.HabitTemplate;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record HabitTemplateResponse(
        UUID id,
        String name,
        String emoji,
        HabitType habitType,
        String color,
        BigDecimal goalValue,
        String goalUnit,
        String goalPeriod
) {
    public static HabitTemplateResponse from(
            HabitTemplate entity
    ) {
        return new HabitTemplateResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmoji(),
                entity.getHabitType(),
                entity.getColor(),
                entity.getGoalValue(),
                entity.getGoalUnit(),
                entity.getGoalPeriod()
        );
    }
}