CREATE TABLE transportations (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       price NUMERIC(12, 2) DEFAULT 0,
                       date TIMESTAMP
);