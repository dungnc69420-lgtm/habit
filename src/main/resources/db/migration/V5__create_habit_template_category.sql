CREATE TABLE habit_category
(
    id         VARCHAR(30) PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    icon       VARCHAR(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO habit_category(id, name, icon)
VALUES ('popular', 'Popular', '🔥'),
       ('health', 'Health', '❤️'),
       ('sports', 'Sports', '🏃'),
       ('home', 'Home', '🏠'),
       ('mind', 'Mind', '🧠');

CREATE TABLE habit_template_category
(
    template_id UUID        NOT NULL,
    category_id VARCHAR(30) NOT NULL,

    PRIMARY KEY (template_id, category_id),

    FOREIGN KEY (template_id)
        REFERENCES habit_template (id),

    FOREIGN KEY (category_id)
        REFERENCES habit_category (id)
);