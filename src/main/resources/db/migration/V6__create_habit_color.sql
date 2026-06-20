CREATE TABLE IF NOT EXISTS habit_color
(
    id           VARCHAR(20) PRIMARY KEY,
    bg_color     VARCHAR(20),
    card_color   VARCHAR(20),
    text_color   VARCHAR(20),
    accent_color VARCHAR(20)
);

ALTER TABLE IF EXISTS habit
    DROP CONSTRAINT IF EXISTS fk_habit_color,
    ADD CONSTRAINT fk_habit_color
        FOREIGN KEY (color)
            REFERENCES habit_color (id);

ALTER TABLE IF EXISTS habit_template
    DROP CONSTRAINT IF EXISTS fk_habit_template_color,
    ADD CONSTRAINT fk_habit_template_color
        FOREIGN KEY (color)
            REFERENCES habit_color (id);

INSERT INTO habit_color
VALUES ('pink', '#FFF0F3', '#FFD6E0', '#C9184A', '#FF4D6D'),
       ('purple', '#F5F0FF', '#E0D4FF', '#6A0DAD', '#7B2FBE'),
       ('blue', '#EBF5FF', '#BFD7FF', '#1A56DB', '#3B82F6'),
       ('teal', '#E6FAF5', '#B2EBD9', '#0D9488', '#14B8A6'),
       ('amber', '#FFFBEB', '#FDE68A', '#B45309', '#F59E0B');