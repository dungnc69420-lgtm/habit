package com.habit.dto.request;

import com.habit.enums.GoalUnitCategory;

public record GoalUnitRequest(
        String name,
        String symbol,
        GoalUnitCategory category
) {
}