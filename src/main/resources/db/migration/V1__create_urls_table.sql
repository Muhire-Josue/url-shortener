CREATE TABLE IF NOT EXISTS urls (
    id BIGSERIAL PRIMARY KEY,
    original_url TEXT NOT NULL,
    short_url TEXT NOT NULL,
    created_date DATE NOT NULL
);
