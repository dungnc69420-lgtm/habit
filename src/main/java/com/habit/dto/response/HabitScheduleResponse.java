package com.habit.dto.response;

import com.habit.enums.ScheduleType;

import java.util.List;

public record HabitScheduleResponse(
        ScheduleType scheduleType,
        Integer targetDays,
        List<Integer> selectedDays
) {
}