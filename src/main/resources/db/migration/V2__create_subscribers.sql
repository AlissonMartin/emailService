CREATE TABLE subscribers (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       topic_id BIGINT REFERENCES topics(id),
                       created_at TIMESTAMP DEFAULT now(),
                       updated_at TIMESTAMP,
                       deleted_at TIMESTAMP
);