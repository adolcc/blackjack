CREATE DATABASE IF NOT EXISTS blackjack;
USE blackjack;

CREATE TABLE IF NOT EXISTS players (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    games_played INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insertar datos de prueba (opcional)
INSERT IGNORE INTO players (id, name, balance, games_played) VALUES
('1', 'Alice', 1500.00, 10),
('2', 'Bob', 1200.50, 8),
('3', 'Charlie', 800.75, 5);