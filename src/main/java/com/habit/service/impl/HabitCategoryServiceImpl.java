package com.habit.service.impl;

import com.habit.dto.response.HabitCategoryResponse;
import com.habit.repository.HabitCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HabitCategoryServiceImpl {
    
    HabitCategoryRepository repository;
    
    @Cacheable("habitCategories")
    public List<HabitCategoryResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(HabitCategoryResponse::from)
                .toList();
    }
}
