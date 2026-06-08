package com.habit.controller;

import com.habit.dto.request.HabitTemplateRequest;
import com.habit.dto.response.HabitTemplateResponse;
import com.habit.service.impl.HabitTemplateServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habit-templates")
@RequiredArgsConstructor
public class HabitTemplateController {

    private final HabitTemplateServiceImpl service;

    @GetMapping
    public List<HabitTemplateResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public HabitTemplateResponse getById(
            @PathVariable UUID id
    ) {
        return service.getById(id);
    }

    @PostMapping
    public HabitTemplateResponse create(
            @RequestBody @Valid HabitTemplateRequest request
    ) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public HabitTemplateResponse update(
            @PathVariable UUID id,
            @RequestBody @Valid HabitTemplateRequest request
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