package com.habit.repository;

import com.habit.model.HabitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HabitTemplateRepository extends JpaRepository<HabitTemplate, UUID> {
}
