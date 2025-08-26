CREATE TABLE topics (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       interval_value INTEGER,
                       interval_unit INTEGER,
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP,
                       deleted_at TIMESTAMP
);