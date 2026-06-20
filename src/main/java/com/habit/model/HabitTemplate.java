package com.habit.model;

import com.habit.enums.GoalPeriod;
import com.habit.enums.HabitType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
import java.util.HashSet;
import java.util.Set;
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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "habit_type")
    HabitType habitType;
    
    String color;
    
    @Column(name = "goal_value")
    BigDecimal goalValue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_unit_id")
    GoalUnit goalUnit;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "goal_period")
    GoalPeriod goalPeriod;
    
    @ManyToMany
    @JoinTable(
            name = "habit_template_category",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<HabitCategory> categories = new HashSet<>();
}