CREATE TABLE todo
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    completed   BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);