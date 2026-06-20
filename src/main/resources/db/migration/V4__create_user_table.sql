CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255),

    name VARCHAR(255) NOT NULL,

    avatar_url TEXT,

    provider VARCHAR(20) NOT NULL,

    provider_id VARCHAR(255),

    email_verified BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),

    updated_at TIMESTAMP,
    updated_by VARCHAR(50)
);

CREATE INDEX idx_user_email
    ON users(email);

CREATE INDEX idx_user_provider_id
    ON users(provider_id);