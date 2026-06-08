package com.habit.model;

import com.habit.enums.HabitType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "habit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Habit extends AuditableEntity {

    @Id
    UUID id;

    String name;

    String description;

    @Column(name = "group_name")
    String groupName;

    String status;

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

    @Column(name = "start_time")
    LocalTime startTime;

    @Column(name = "end_time")
    LocalTime endTime;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @Column(name = "reminder_enabled")
    Boolean reminderEnabled;

    @Column(name = "reminder_time")
    LocalTime reminderTime;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "habit")
    List<HabitLog> habitLogs = new ArrayList<>();
}