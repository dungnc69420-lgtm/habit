package com.habit.controller;

import com.habit.dto.response.HabitCategoryResponse;
import com.habit.service.impl.HabitCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habit-categories")
@RequiredArgsConstructor
public class HabitCategoryController {
    
    private final HabitCategoryServiceImpl service;
    
    @GetMapping
    public List<HabitCategoryResponse> getAll() {
        return service.getAll();
    }
}