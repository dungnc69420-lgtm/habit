package com.habit.dto.request;

import com.habit.enums.ScheduleType;

import java.util.List;

public record CreateHabitScheduleRequest(
        ScheduleType scheduleType,
        Integer targetDays,
        List<Integer> selectedDays
) {
}