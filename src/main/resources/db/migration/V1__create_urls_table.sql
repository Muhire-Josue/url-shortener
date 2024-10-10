CREATE TABLE IF NOT EXISTS urls (
    id BIGSERIAL PRIMARY KEY,
    original_url TEXT NOT NULL,
    url_id VARCHAR(20) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);
