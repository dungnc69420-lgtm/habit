package com.habit.service;

import com.habit.dto.request.CreateHabitRequest;
import com.habit.dto.request.UpdateHabitRequest;
import com.habit.dto.response.HabitResponse;

import java.util.List;
import java.util.UUID;

public interface HabitService {
    
    HabitResponse create(CreateHabitRequest request);
    
    HabitResponse update(UUID habitId, UpdateHabitRequest request);
    
    HabitResponse getById(UUID habitId);
    
    List<HabitResponse> getAll();
    
    void delete(UUID habitId);
}