package com.habit.repository;

import com.habit.model.HabitCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HabitCategoryRepository extends JpaRepository<HabitCategory, UUID> {
}
