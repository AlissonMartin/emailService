CREATE TABLE email_queue (
                       id BIGSERIAL PRIMARY KEY,
                       topic_id BIGINT REFERENCES topics(id),
                       email_id BIGINT REFERENCES emails(id),
                       created_at TIMESTAMP DEFAULT now(),
                       sent_at TIMESTAMP,
                       updated_at TIMESTAMP,
                       deleted_at TIMESTAMP
);