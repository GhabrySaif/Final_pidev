-- Création de la table Utilisateurs
CREATE TABLE IF NOT EXISTS utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('Admin', 'Livreur', 'Client') NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Création de la table Colis
CREATE TABLE IF NOT EXISTS colis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    poids DECIMAL(10, 2) NOT NULL,
    volume DECIMAL(10, 2) NOT NULL,
    description TEXT,
    date_envoi TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut ENUM('En attente', 'En transit', 'Livré', 'Annulé') NOT NULL,
    utilisateur_id INT NOT NULL, 
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Création de la table Livraisons
CREATE TABLE IF NOT EXISTS livraisons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    colis_id INT NOT NULL,
    livreur_id INT NOT NULL,
    date_livraison TIMESTAMP,
    statut ENUM('En cours', 'Complète', 'Échouée') NOT NULL,
    FOREIGN KEY (colis_id) REFERENCES colis(id) ON DELETE CASCADE,
    FOREIGN KEY (livreur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Création de la table Paiements
CREATE TABLE IF NOT EXISTS paiements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    colis_id INT NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    date_paiement TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut ENUM('Payé', 'En attente', 'Non payé') NOT NULL,
    FOREIGN KEY (colis_id) REFERENCES colis(id) ON DELETE CASCADE
);

-- Création de la table Historique des livraisons
CREATE TABLE IF NOT EXISTS historique_livraisons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livraison_id INT NOT NULL,
    statut VARCHAR(255) NOT NULL,
    date_heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (livraison_id) REFERENCES livraisons(id) ON DELETE CASCADE
);

-- Création de la table Factures
CREATE TABLE IF NOT EXISTS factures (
    id INT AUTO_INCREMENT PRIMARY KEY,
    colis_id INT NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    date_emission TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_echeance datetime,
    statut ENUM('Payée', 'En attente', 'Non payée') NOT NULL,
    FOREIGN KEY (colis_id) REFERENCES colis(id) ON DELETE CASCADE
);

-- Création de la table Statistiques (pour stocker des informations de suivi)
CREATE TABLE IF NOT EXISTS statistiques (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT NOT NULL,
    date DATE NOT NULL,
    nombre_colis_livrés INT DEFAULT 0,
    montant_total DECIMAL(10, 2) DEFAULT 0,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);
