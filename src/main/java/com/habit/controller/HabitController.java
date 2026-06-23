package com.habit.controller;

import com.habit.dto.request.CreateHabitRequest;
import com.habit.dto.request.UpdateHabitRequest;
import com.habit.dto.response.HabitResponse;
import com.habit.service.HabitService;
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
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public HabitResponse create(
            @RequestBody CreateHabitRequest request
    ) {
        return habitService.create(request);
    }

    @GetMapping("/{habitId}")
    public HabitResponse getById(@PathVariable UUID habitId) {
        return habitService.getById(habitId);
    }

    @GetMapping
    public List<HabitResponse> getAll() {
        return habitService.getAll();
    }

    @PutMapping("/{habitId}")
    public HabitResponse update(
            @PathVariable UUID habitId,
            @RequestBody UpdateHabitRequest request
    ) {
        return habitService.update(
                habitId,
                request
        );
    }

    @DeleteMapping("/{habitId}")
    public void delete(
            @PathVariable UUID habitId
    ) {
        habitService.delete(habitId);
    }
}