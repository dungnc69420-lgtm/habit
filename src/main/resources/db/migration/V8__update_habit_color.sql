ALTER TABLE IF EXISTS habit_template
    DROP CONSTRAINT IF EXISTS fk_habit_template_color;

UPDATE habit_template ht
SET color = hc.bg_color
FROM habit_color AS hc;

UPDATE habit_color
SET id = bg_color;

ALTER TABLE IF EXISTS habit_template
    ADD CONSTRAINT fk_habit_template_color
        FOREIGN KEY (color)
            REFERENCES habit_color (id);