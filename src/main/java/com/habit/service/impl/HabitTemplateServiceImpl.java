package com.habit.service.impl;

import com.habit.dto.request.HabitTemplateRequest;
import com.habit.dto.response.HabitTemplateResponse;
import com.habit.model.GoalUnit;
import com.habit.model.HabitTemplate;
import com.habit.repository.HabitTemplateRepository;
import com.habit.service.GoalUnitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HabitTemplateServiceImpl {
    
    HabitTemplateRepository repository;
    GoalUnitService goalUnitService;
    
    @Cacheable("habitTemplates")
    public List<HabitTemplateResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(HabitTemplateResponse::from)
                .toList();
    }
    
    public HabitTemplateResponse getById(UUID id) {
        HabitTemplate template = getHabitTemplate(id);
        
        return HabitTemplateResponse.from(template);
    }
    
    private HabitTemplate getHabitTemplate(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Habit template not found"));
    }
    
    public HabitTemplateResponse create(HabitTemplateRequest request) {
        
        GoalUnit goalUnit = getGoalUnit(request);
        
        HabitTemplate template = HabitTemplate.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .emoji(request.emoji())
                .habitType(request.habitType())
                .color(request.color())
                .goalValue(request.goalValue())
                .goalUnit(goalUnit)
                .goalPeriod(request.goalPeriod())
                .build();
        
        return HabitTemplateResponse.from(
                repository.save(template)
        );
    }
    
    private GoalUnit getGoalUnit(HabitTemplateRequest request) {
        return goalUnitService.getByNameAndCreator(request.goalUnit());
    }
    
    public HabitTemplateResponse update(
            UUID id,
            HabitTemplateRequest request
    ) {
        
        HabitTemplate template = getHabitTemplate(id);
        
        template.setName(request.name());
        template.setEmoji(request.emoji());
        template.setHabitType(request.habitType());
        template.setColor(request.color());
        template.setGoalValue(request.goalValue());
        template.setGoalUnit(getGoalUnit(request));
        template.setGoalPeriod(request.goalPeriod());
        
        return HabitTemplateResponse.from(
                repository.save(template)
        );
    }
    
    public void delete(UUID id) {
        
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Habit template not found");
        }
        
        repository.deleteById(id);
    }
}
