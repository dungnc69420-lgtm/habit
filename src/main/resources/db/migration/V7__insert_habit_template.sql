ALTER TABLE habit_template
    ADD COLUMN code VARCHAR(50) UNIQUE;

INSERT INTO habit_template (id,
                            code,
                            name,
                            emoji,
                            habit_type,
                            color,
                            goal_value,
                            goal_unit_id,
                            created_by)
VALUES (gen_random_uuid(),
        'walk',
        'Walk',
        '🚶',
        'BUILD',
        'pink',
        30,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'sleep',
        'Sleep',
        '🛏️',
        'BUILD',
        'purple',
        8,
        (SELECT id FROM goal_unit WHERE symbol = 'hr'),
        'SYSTEM'),

       (gen_random_uuid(),
        'drink_water',
        'Drink water',
        '💧',
        'BUILD',
        'blue',
        8,
        (SELECT id FROM goal_unit WHERE symbol = 'cup'),
        'SYSTEM'),

       (gen_random_uuid(),
        'meditation',
        'Meditation',
        '🧘',
        'BUILD',
        'purple',
        20,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'run',
        'Run',
        '🏃',
        'BUILD',
        'pink',
        30,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'stand',
        'Stand',
        '🧍',
        'BUILD',
        'teal',
        12,
        (SELECT id FROM goal_unit WHERE symbol = 'times'),
        'SYSTEM'),

       (gen_random_uuid(),
        'cycling',
        'Cycling',
        '🚴',
        'BUILD',
        'blue',
        45,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'workout',
        'Workout',
        '💪',
        'BUILD',
        'amber',
        60,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'active_calorie',
        'Active Calorie',
        '🔥',
        'BUILD',
        'pink',
        500,
        (SELECT id FROM goal_unit WHERE symbol = 'kcal'),
        'SYSTEM'),

       (gen_random_uuid(),
        'burn_calorie',
        'Burn Calorie',
        '🔥',
        'BUILD',
        'amber',
        300,
        (SELECT id FROM goal_unit WHERE symbol = 'kcal'),
        'SYSTEM'),

       (gen_random_uuid(),
        'exercise',
        'Exercise',
        '🏋️',
        'BUILD',
        'teal',
        45,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'read',
        'Read a book',
        '📚',
        'BUILD',
        'blue',
        30,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'journal',
        'Journal',
        '📓',
        'BUILD',
        'purple',
        15,
        (SELECT id FROM goal_unit WHERE symbol = 'min'),
        'SYSTEM'),

       (gen_random_uuid(),
        'no_sugar',
        'No sugar',
        '🍬',
        'QUIT',
        'teal',
        1,
        (SELECT id FROM goal_unit WHERE symbol = 'day'),
        'SYSTEM'),

       (gen_random_uuid(),
        'vitamins',
        'Take vitamins',
        '💊',
        'BUILD',
        'pink',
        1,
        (SELECT id FROM goal_unit WHERE symbol = 'day'),
        'SYSTEM');

INSERT INTO habit_template_category(template_id, category_id)

SELECT id, 'popular'
FROM habit_template
WHERE code IN (
               'walk',
               'sleep',
               'drink_water',
               'meditation',
               'run',
               'workout',
               'active_calorie',
               'read'
    )

UNION ALL

SELECT id, 'sports'
FROM habit_template
WHERE code IN (
               'walk',
               'run',
               'cycling',
               'workout',
               'exercise'
    )

UNION ALL

SELECT id, 'health'
FROM habit_template
WHERE code IN (
               'sleep',
               'drink_water',
               'stand',
               'active_calorie',
               'burn_calorie',
               'no_sugar',
               'vitamins'
    )

UNION ALL

SELECT id, 'mind'
FROM habit_template
WHERE code IN (
               'meditation',
               'read',
               'journal'
    );