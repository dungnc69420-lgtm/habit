package com.habit.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Table(name = "goal_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GoalUnit extends AuditableEntity {

    @Id
    UUID id;

    @Column(nullable = false, length = 50)
    String name;

    @Column(length = 20)
    String symbol;

    @Column(length = 30)
    String category;

    @Column(name = "is_system")
    Boolean isSystem;
}