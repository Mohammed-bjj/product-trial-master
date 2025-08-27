-- Création du schéma de la table users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL
);

-- Création du schéma de la table products
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    quantity INTEGER NOT NULL,
    shell_id BIGINT NOT NULL,
    internal_reference VARCHAR(255) NOT NULL,
    inventory_status ENUM('INSTOCK', 'LOWSTOCK', 'OUTOFSTOCK') NOT NULL,
    rating INTEGER CHECK (rating >= 1 AND rating <= 5)
);