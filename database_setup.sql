-- Complete Database Setup for Package Management System
-- Execute this script in your MySQL database

-- Create and use database
CREATE DATABASE IF NOT EXISTS deliverymanagement;
USE deliverymanagement;

-- Drop existing tables if they exist (for clean setup)
DROP TABLE IF EXISTS historique_livraisons;
DROP TABLE IF EXISTS statistiques;
DROP TABLE IF EXISTS livraisons;
DROP TABLE IF EXISTS colis;
DROP TABLE IF EXISTS utilisateurs;

-- Create Users table
CREATE TABLE utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Livreur', 'Client') NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Packages table
CREATE TABLE colis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT NOT NULL,
    description TEXT,
    poids DECIMAL(10, 2) NOT NULL,
    volume DECIMAL(10, 3) NOT NULL,
    adresse_destination VARCHAR(500) NOT NULL,
    statut ENUM('En attente', 'En transit', 'Livré', 'Annulé') DEFAULT 'En attente',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Create Deliveries table
CREATE TABLE livraisons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    colis_id INT NOT NULL,
    livreur_id INT NOT NULL,
    date_livraison TIMESTAMP,
    statut ENUM('En cours', 'Complète', 'Échouée') NOT NULL,
    FOREIGN KEY (colis_id) REFERENCES colis(id) ON DELETE CASCADE,
    FOREIGN KEY (livreur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Create Delivery History table
CREATE TABLE historique_livraisons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livraison_id INT NOT NULL,
    statut VARCHAR(255) NOT NULL,
    date_heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (livraison_id) REFERENCES livraisons(id) ON DELETE CASCADE
);

-- Create Statistics table
CREATE TABLE statistiques (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT NOT NULL,
    date DATE NOT NULL,
    nombre_colis_livrés INT DEFAULT 0,
    montant_total DECIMAL(10, 2) DEFAULT 0,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Insert test users
INSERT INTO utilisateurs (username, password, role, email, phone) VALUES
('admin', 'admin123', 'Admin', 'admin@example.com', '0123456789'),
('livreur1', 'livreur123', 'Livreur', 'livreur1@example.com', '0987654321'),
('livreur2', 'livreur456', 'Livreur', 'livreur2@example.com', '0987654322'),
('client1', 'client123', 'Client', 'client1@example.com', '0555123456'),
('client2', 'client456', 'Client', 'client2@example.com', '0555123457'),
('client3', 'client789', 'Client', 'client3@example.com', '0555123458');

-- Insert test packages
INSERT INTO colis (utilisateur_id, description, poids, volume, adresse_destination, statut) VALUES
(4, 'Documents importants', 0.5, 0.01, '123 Rue de la Paix, Paris', 'En attente'),
(4, 'Livre rare', 1.2, 0.05, '456 Avenue des Champs, Lyon', 'En transit'),
(5, 'Vêtements', 2.5, 0.15, '789 Boulevard Saint-Michel, Marseille', 'Livré'),
(5, 'Électronique fragile', 3.0, 0.08, '321 Rue de Rivoli, Toulouse', 'En attente'),
(6, 'Cadeau d\'anniversaire', 1.8, 0.12, '654 Place Bellecour, Nice', 'En transit'),
(6, 'Produits cosmétiques', 0.8, 0.03, '987 Cours Mirabeau, Bordeaux', 'Livré'),
(4, 'Matériel de bureau', 5.0, 0.25, '147 Rue du Commerce, Lille', 'En attente'),
(5, 'Jouets pour enfants', 2.2, 0.18, '258 Avenue de la République, Nantes', 'En transit');

-- Insert test deliveries
INSERT INTO livraisons (colis_id, livreur_id, date_livraison, statut) VALUES
(2, 2, '2025-01-15 14:30:00', 'Complète'),
(3, 2, '2025-01-16 10:15:00', 'Complète'),
(5, 3, '2025-01-17 16:45:00', 'En cours'),
(6, 2, '2025-01-18 09:20:00', 'Complète'),
(8, 3, NULL, 'En cours');

-- Insert delivery history
INSERT INTO historique_livraisons (livraison_id, statut) VALUES
(1, 'Colis pris en charge'),
(1, 'En route vers la destination'),
(1, 'Livré avec succès'),
(2, 'Colis pris en charge'),
(2, 'Livré avec succès'),
(3, 'Colis assigné au livreur'),
(4, 'Colis pris en charge'),
(4, 'Livré avec succès'),
(5, 'Colis assigné au livreur');

-- Insert statistics
INSERT INTO statistiques (utilisateur_id, date, nombre_colis_livrés, montant_total) VALUES
(2, '2025-01-15', 1, 25.00),
(2, '2025-01-16', 1, 30.00),
(3, '2025-01-17', 0, 0.00),
(2, '2025-01-18', 1, 20.00);

-- Display setup confirmation
SELECT 'Database setup completed successfully!' as Status;
SELECT COUNT(*) as Total_Users FROM utilisateurs;
SELECT COUNT(*) as Total_Packages FROM colis;
SELECT COUNT(*) as Total_Deliveries FROM livraisons;
