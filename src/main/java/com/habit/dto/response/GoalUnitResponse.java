package com.habit.dto.response;

import com.habit.enums.GoalUnitCategory;
import com.habit.model.GoalUnit;

import java.util.UUID;

public record GoalUnitResponse(
        UUID id,
        String name,
        String symbol,
        GoalUnitCategory category,
        Boolean isSystem
) {
    public static GoalUnitResponse from(GoalUnit entity) {
        return new GoalUnitResponse(
                entity.getId(),
                entity.getName(),
                entity.getSymbol(),
                entity.getCategory(),
                entity.isSystem()
        );
    }
}