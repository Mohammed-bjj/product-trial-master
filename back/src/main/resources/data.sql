-- Insertion des données de test
-- Mot de passe pour tous les comptes: figuig
INSERT IGNORE INTO users (email, password, first_name, last_name, role) VALUES
('admin@admin.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye/Ci/QcuWZ4KkMqB8FH.vEyVDRDtR8.S', 'Admin', 'System', 'ADMIN'),
('user1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye/Ci/QcuWZ4KkMqB8FH.vEyVDRDtR8.S', 'Jean', 'Dupont', 'USER'),
('user2@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye/Ci/QcuWZ4KkMqB8FH.vEyVDRDtR8.S', 'Marie', 'Martin', 'USER'),
('user3@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye/Ci/QcuWZ4KkMqB8FH.vEyVDRDtR8.S', 'Pierre', 'Durand', 'USER');