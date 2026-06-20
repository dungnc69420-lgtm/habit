package com.habit.service;

import com.habit.dto.request.GoalUnitRequest;
import com.habit.dto.response.GoalUnitResponse;
import com.habit.model.GoalUnit;

import java.util.List;
import java.util.UUID;

public interface GoalUnitService {

    List<GoalUnitResponse> getAllDefault();

    GoalUnitResponse getById(UUID id);

    GoalUnitResponse create(GoalUnitRequest request);

    GoalUnitResponse update(UUID id, GoalUnitRequest request);

    void delete(UUID id);
    
    GoalUnit getByNameAndCreator(String name);
}