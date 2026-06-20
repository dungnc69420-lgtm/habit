package com.habit.dto.response;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitType;
import com.habit.model.HabitCategory;
import com.habit.model.HabitTemplate;
import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record HabitTemplateResponse(
        UUID id,
        String name,
        String icon,
        HabitType habitType,
        String color,
        BigDecimal goalValue,
        String goalUnit,
        GoalPeriod goalPeriod,
        List<String> category
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
                entity.getGoalUnit() == null ? Strings.EMPTY : entity.getGoalUnit().getName(),
                GoalPeriod.DAILY,
                entity.getCategories().stream().map(HabitCategory::getId).toList()
        );
    }
}