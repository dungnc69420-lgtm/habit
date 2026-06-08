CREATE TABLE IF NOT EXISTS habit
(
    id               UUID PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    description      TEXT,
    group_name       VARCHAR(100),
    status           VARCHAR(20)  NOT NULL,
    emoji            VARCHAR(20),
    habit_type       VARCHAR(20),
    color            VARCHAR(20),

    goal_value       NUMERIC(10, 2),
    goal_unit        VARCHAR(30),
    goal_period      VARCHAR(20),

    start_time       TIME,
    end_time         TIME,

    start_date       TIMESTAMP,
    end_date         TIMESTAMP,

    reminder_enabled BOOLEAN,
    reminder_time    TIME,

    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by       VARCHAR(20),

    updated_at       TIMESTAMP,
    updated_by       VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS habit_log
(
    id              UUID PRIMARY KEY,
    habit_id        UUID           NOT NULL,
    log_date        DATE           NOT NULL,
    completed_value NUMERIC(10, 2) NOT NULL,

    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(50),

    updated_at      TIMESTAMP,
    updated_by      VARCHAR(20),

    CONSTRAINT fk_habit_log_habit
        FOREIGN KEY (habit_id)
            REFERENCES habit (id),

    CONSTRAINT uk_habit_log
        UNIQUE (habit_id, log_date)
);

CREATE TABLE IF NOT EXISTS habit_schedule
(
    habit_id      UUID PRIMARY KEY,
    schedule_type VARCHAR(30) NOT NULL,
    target_days   INTEGER,
    selected_days JSONB,

    FOREIGN KEY (habit_id)
        REFERENCES habit (id)
);

CREATE TABLE IF NOT EXISTS habit_template
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    emoji       VARCHAR(20),
    habit_type  VARCHAR(20),
    color       VARCHAR(20),

    goal_value  NUMERIC(10, 2),
    goal_unit   VARCHAR(30),
    goal_period VARCHAR(20),

    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by  VARCHAR(20),

    updated_at  TIMESTAMP,
    updated_by  VARCHAR(20)
);
