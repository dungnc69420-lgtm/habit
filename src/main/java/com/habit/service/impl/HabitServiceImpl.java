package com.habit.service.impl;

import com.habit.dto.request.CreateHabitRequest;
import com.habit.dto.request.UpdateHabitRequest;
import com.habit.dto.response.HabitResponse;
import com.habit.enums.HabitStatus;
import com.habit.exceptions.Exceptions;
import com.habit.model.GoalUnit;
import com.habit.model.Habit;
import com.habit.model.HabitSchedule;
import com.habit.repository.GoalUnitRepository;
import com.habit.repository.HabitRepository;
import com.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class HabitServiceImpl implements HabitService {
    
    private final HabitRepository habitRepository;
    private final GoalUnitRepository goalUnitRepository;
    
    @Override
    public HabitResponse create(CreateHabitRequest request) {
        
        GoalUnit goalUnit = getGoalUnit(request.goalUnitId());
        
        Habit habit = Habit.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .status(HabitStatus.ACTIVE)
                .emoji(request.emoji())
                .description(request.description())
                .groupName(request.groupName())
                .habitType(request.habitType())
                .color(request.color())
                .goalValue(request.goalValue())
                .goalUnit(goalUnit)
                .goalPeriod(request.goalPeriod())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
        
        if (request.schedule() != null) {
            
            HabitSchedule schedule = HabitSchedule.builder()
                    .habit(habit)
                    .scheduleType(request.schedule().scheduleType())
                    .targetDays(request.schedule().targetDays())
                    .selectedDays(request.schedule().selectedDays())
                    .build();
            
            habit.setSchedule(schedule);
        }
        
        habit = habitRepository.save(habit);
        
        return HabitResponse.mapToResponse(habit);
    }
    
    @Override
    public HabitResponse getById(UUID habitId) {
        
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        Exceptions.notFound("Habit", habitId)
                );
        
        return HabitResponse.mapToResponse(habit);
    }
    
    @Override
    public List<HabitResponse> getAll() {
        
        return habitRepository.findAll()
                .stream()
                .map(HabitResponse::mapToResponse)
                .toList();
    }
    
    @Override
    public HabitResponse update(
            UUID habitId,
            UpdateHabitRequest request
    ) {
        
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        Exceptions.notFound("Habit", habitId)
                );
        
        habit.setName(request.name());
        habit.setDescription(request.description());
        habit.setGroupName(request.groupName());
        habit.setHabitType(request.habitType());
        habit.setColor(request.color());
        habit.setGoalPeriod(request.goalPeriod());
        
        if (!request.goalUnitId().equals(habit.getGoalUnit().getId())) {
            GoalUnit goalUnit = getGoalUnit(request.goalUnitId());
            habit.setGoalUnit(goalUnit);
        }
        
        habit = habitRepository.save(habit);
        
        return HabitResponse.mapToResponse(habit);
    }
    
    private GoalUnit getGoalUnit(UUID id) {
        return goalUnitRepository.findById(id).orElseThrow(() ->
                Exceptions.notFound("GoalUnit", id)
        );
    }
    
    @Override
    public void delete(UUID habitId) {
        habitRepository.deleteById(habitId);
    }
}