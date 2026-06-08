package com.habit.dto.response;

import java.util.UUID;

public record GoalUnitResponse(
        UUID id,
        String name,
        String symbol,
        String category,
        Boolean isSystem
) {
}