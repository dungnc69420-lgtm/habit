package com.habit.dto.request;

import java.util.List;

public record UpdateHabitScheduleRequest(
        String scheduleType,
        Integer targetDays,
        List<Integer> selectedDays
) {
}