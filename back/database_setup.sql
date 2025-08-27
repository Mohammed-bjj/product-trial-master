-- Script SQL pour créer la base de données ALTEN Shop
-- Base de données MySQL

-- Créer la base de données
DROP DATABASE IF EXISTS alten_shop;
CREATE DATABASE alten_shop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE alten_shop;

-- Table des utilisateurs
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insérer des données de test
INSERT INTO users (email, password, first_name, last_name, role) VALUES
('admin@admin.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Admin', 'System', 'ADMIN'),
('user1@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Jean', 'Dupont', 'USER'),
('user2@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Marie', 'Martin', 'USER'),
('user3@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Pierre', 'Durand', 'USER');

-- Afficher les données insérées
SELECT * FROM users;

-- Informations sur la base de données
SHOW TABLES;
DESCRIBE users;