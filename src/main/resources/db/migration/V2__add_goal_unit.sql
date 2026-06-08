CREATE TABLE IF NOT EXISTS goal_unit
(
    id UUID PRIMARY KEY,

    name VARCHAR(50) NOT NULL,
    symbol VARCHAR(20),

    category VARCHAR(30),

    is_system BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),

    updated_at TIMESTAMP,
    updated_by VARCHAR(50)
);

ALTER TABLE IF EXISTS habit
    ADD COLUMN goal_unit_id UUID;

ALTER TABLE IF EXISTS habit
    ADD CONSTRAINT fk_habit_goal_unit
        FOREIGN KEY (goal_unit_id)
            REFERENCES goal_unit(id);

ALTER TABLE IF EXISTS habit_template
    ADD COLUMN goal_unit_id UUID;

ALTER TABLE IF EXISTS habit_template
    ADD CONSTRAINT fk_habit_template_goal_unit
        FOREIGN KEY (goal_unit_id)
            REFERENCES goal_unit(id);