package com.habit.repository;

import com.habit.model.GoalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalUnitRepository
        extends JpaRepository<GoalUnit, UUID> {
    
    List<GoalUnit> findAllByIsSystemTrue();
    
    Optional<GoalUnit> findByNameAndCreatedBy(String name, String createdBy);
    
    Optional<GoalUnit> findByNameAndIsSystemTrue(String name);
}