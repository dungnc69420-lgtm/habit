package com.habit.dto.response;

import com.habit.model.HabitCategory;
import lombok.Builder;

@Builder
public record HabitCategoryResponse(
        String id,
        String label,
        String icon
) {
    public static HabitCategoryResponse from(
            HabitCategory entity
    ) {
        return new HabitCategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getIcon()
        );
    }
}