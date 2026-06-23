ALTER TABLE habit
    DROP COLUMN IF EXISTS goal_unit,
    DROP COLUMN IF EXISTS reminder_time,
    DROP COLUMN IF EXISTS reminder_enabled;
