CREATE TABLE emails (
                            id BIGSERIAL PRIMARY KEY,
                            address VARCHAR(255),
                            subject VARCHAR(255),
                            body TEXT,
                            created_at TIMESTAMP DEFAULT now(),
                            updated_at TIMESTAMP,
                            deleted_at TIMESTAMP
);