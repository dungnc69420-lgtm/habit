package com.habit.service.impl;

import com.habit.config.SecurityUtils;
import com.habit.dto.request.GoalUnitRequest;
import com.habit.dto.response.GoalUnitResponse;
import com.habit.model.GoalUnit;
import com.habit.repository.GoalUnitRepository;
import com.habit.service.GoalUnitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalUnitServiceImpl implements GoalUnitService {
    
    private final GoalUnitRepository repository;
    
    @Override
    public List<GoalUnitResponse> getAllDefault() {
        return repository.findAllByIsSystemTrue()
                .stream()
                .map(GoalUnitResponse::from)
                .toList();
    }
    
    @Override
    public GoalUnitResponse getById(UUID id) {
        
        GoalUnit unit = get(id);
        
        return GoalUnitResponse.from(unit);
    }
    
    @Override
    public GoalUnit getByNameAndCreator(String name) {
        
        return repository.findByNameAndCreatedBy(name, SecurityUtils.getCurrentUserName())
                .or(() -> repository.findByNameAndIsSystemTrue(name))
                .orElseThrow(() ->
                        new EntityNotFoundException("Goal unit not found"));
    }
    
    private GoalUnit get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Goal unit not found"));
    }
    
    @Override
    public GoalUnitResponse create(
            GoalUnitRequest request
    ) {
        
        // TODO: name is unique
        GoalUnit unit = GoalUnit.builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .symbol(request.symbol())
                .category(request.category())
                .isSystem(false)
                .build();
        
        return GoalUnitResponse.from(
                repository.save(unit)
        );
    }
    
    @Override
    public GoalUnitResponse update(
            UUID id,
            GoalUnitRequest request
    ) {
        
        GoalUnit unit = get(id);
        
        unit.setName(request.name());
        unit.setSymbol(request.symbol());
        unit.setCategory(request.category());
        
        return GoalUnitResponse.from(
                repository.save(unit)
        );
    }
    
    @Override
    public void delete(UUID id) {
        
        GoalUnit unit = get(id);
        
        if (Boolean.TRUE.equals(unit.isSystem())) {
            throw new IllegalStateException(
                    "System goal unit cannot be deleted"
            );
        }
        
        repository.delete(unit);
    }
}