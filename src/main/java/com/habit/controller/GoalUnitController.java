package com.habit.controller;

import com.habit.dto.request.GoalUnitRequest;
import com.habit.dto.response.GoalUnitResponse;
import com.habit.service.GoalUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/goal-units")
@RequiredArgsConstructor
public class GoalUnitController {
    
    private final GoalUnitService service;
    
    @GetMapping("/default")
    public List<GoalUnitResponse> getAllDefault() {
        return service.getAllDefault();
    }
    
    @GetMapping("/{id}")
    public GoalUnitResponse getById(
            @PathVariable UUID id
    ) {
        return service.getById(id);
    }
    
    @PostMapping
    public GoalUnitResponse create(
            @RequestBody @Valid GoalUnitRequest request
    ) {
        return service.create(request);
    }
    
    @PutMapping("/{id}")
    public GoalUnitResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid GoalUnitRequest request
    ) {
        return service.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id
    ) {
        service.delete(id);
    }
}