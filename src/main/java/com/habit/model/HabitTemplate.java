package com.habit.model;

import com.habit.enums.HabitType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "habit_template")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HabitTemplate extends AuditableEntity {
    
    @Id
    UUID id;
    
    String name;
    
    String emoji;
    
    @Column(name = "habit_type")
    HabitType habitType;
    
    String color;
    
    @Column(name = "goal_value")
    BigDecimal goalValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_unit_id")
    GoalUnit goalUnit;
    
    @Column(name = "goal_period")
    String goalPeriod;
}