CREATE TABLE email (
                            id BIGSERIAL PRIMARY KEY,
                            subscriber_id BIGINT REFERENCES subscriber(id),
                            subject VARCHAR(255),
                            body VARCHAR(255)
);