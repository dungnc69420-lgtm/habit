INSERT INTO goal_unit (id, name, symbol, category, is_system)
VALUES

-- COUNT
(gen_random_uuid(), 'Count', 'count', 'COUNT', true),
(gen_random_uuid(), 'Times', 'times', 'COUNT', true),
(gen_random_uuid(), 'Tasks', 'tasks', 'COUNT', true),

-- TIME
(gen_random_uuid(), 'Second', 'sec', 'TIME', true),
(gen_random_uuid(), 'Minute', 'min', 'TIME', true),
(gen_random_uuid(), 'Hour', 'hr', 'TIME', true),
(gen_random_uuid(), 'Day', 'day', 'TIME', true),

-- DISTANCE
(gen_random_uuid(), 'Meter', 'm', 'DISTANCE', true),
(gen_random_uuid(), 'Kilometer', 'km', 'DISTANCE', true),
(gen_random_uuid(), 'Mile', 'mi', 'DISTANCE', true),

-- WEIGHT
(gen_random_uuid(), 'Gram', 'g', 'WEIGHT', true),
(gen_random_uuid(), 'Kilogram', 'kg', 'WEIGHT', true),
(gen_random_uuid(), 'Pound', 'lb', 'WEIGHT', true),

-- VOLUME
(gen_random_uuid(), 'Milliliter', 'ml', 'VOLUME', true),
(gen_random_uuid(), 'Liter', 'L', 'VOLUME', true),
(gen_random_uuid(), 'Cup', 'cup', 'VOLUME', true),
(gen_random_uuid(), 'Glass', 'glass', 'VOLUME', true),
(gen_random_uuid(), 'Bottle', 'bottle', 'VOLUME', true),

-- FITNESS
(gen_random_uuid(), 'Step', 'steps', 'FITNESS', true),
(gen_random_uuid(), 'Repetition', 'reps', 'FITNESS', true),
(gen_random_uuid(), 'Set', 'sets', 'FITNESS', true),
(gen_random_uuid(), 'Workout', 'workout', 'FITNESS', true),

-- HEALTH
(gen_random_uuid(), 'Calorie', 'kcal', 'HEALTH', true),
(gen_random_uuid(), 'Medication', 'pill', 'HEALTH', true),

-- READING
(gen_random_uuid(), 'Page', 'pages', 'READING', true),
(gen_random_uuid(), 'Chapter', 'chapters', 'READING', true),
(gen_random_uuid(), 'Book', 'books', 'READING', true),

-- PRODUCTIVITY
(gen_random_uuid(), 'Word', 'words', 'PRODUCTIVITY', true),
(gen_random_uuid(), 'Pomodoro', 'pomodoro', 'PRODUCTIVITY', true),
(gen_random_uuid(), 'Email', 'emails', 'PRODUCTIVITY', true),

-- LEARNING
(gen_random_uuid(), 'Lesson', 'lessons', 'LEARNING', true),
(gen_random_uuid(), 'Course', 'courses', 'LEARNING', true),
(gen_random_uuid(), 'Flashcard', 'cards', 'LEARNING', true);