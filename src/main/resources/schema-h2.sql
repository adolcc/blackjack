CREATE TABLE IF NOT EXISTS players (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    games_played INT NOT NULL
);