CREATE TABLE trips (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       destination VARCHAR(255),
                       start_date TIMESTAMP,
                       end_date TIMESTAMP,
                       status VARCHAR(20) DEFAULT 'PLANNING',
                       is_private BOOLEAN DEFAULT TRUE,
                       budget NUMERIC(12, 2) DEFAULT 0,
                       spending NUMERIC(12, 2) DEFAULT 0,
                       cover_image_url TEXT,
                       review TEXT,
                       rating INTEGER DEFAULT 1 CHECK (rating BETWEEN 1 AND 5),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);