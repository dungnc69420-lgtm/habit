package com.habit.model;

import com.habit.enums.ScheduleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "habit_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HabitSchedule {
    
    @Id
    @Column(name = "habit_id")
    UUID habitId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "habit_id")
    Habit habit;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_type")
    ScheduleType scheduleType;
    
    @Column(name = "target_days")
    Integer targetDays;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "selected_days")
    List<Integer> selectedDays;
}