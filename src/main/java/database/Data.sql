-- Insertion d'utilisateurs (clients, livreurs, administrateurs)
INSERT INTO utilisateurs (username, password, role, email, phone)
VALUES
    ('admin', 'admin123', 'Admin', 'admin@example.com', '0101234567'),
    ('livreur1', 'livreur123', 'Livreur', 'livreur1@example.com', '0102345678'),
    ('client1', 'client123', 'Client', 'client1@example.com', '0103456789'),
    ('client2', 'client123', 'Client', 'client2@example.com', '0104567890');

-- Insertion de colis
INSERT INTO colis (poids, volume, description, statut, utilisateur_id)
VALUES
    (2.5, 0.02, 'Colis fragile, contient des documents', 'En attente', 3),  -- client1
    (1.0, 0.01, 'Petit colis avec des accessoires électroniques', 'En transit', 4),  -- client2
    (5.0, 0.05, 'Colis alimentaire, périssable', 'Livré', 3),  -- client1
    (3.0, 0.03, 'Vêtements et accessoires', 'Annulé', 4);  -- client2

-- Insertion de livraisons
INSERT INTO livraisons (colis_id, livreur_id, date_livraison, statut)
VALUES
    (1, 2, '2025-02-05 10:00:00', 'En cours'),  -- Colis 1 livré par livreur1
    (2, 2, '2025-02-06 11:00:00', 'Complète'),  -- Colis 2 livré par livreur1
    (3, 2, '2025-02-04 15:00:00', 'Complète'),  -- Colis 3 livré par livreur1
    (4, 2, NULL, 'Échouée');  -- Colis 4 échoué, non livré

-- Insertion de paiements
INSERT INTO paiements (colis_id, montant, statut)
VALUES
    (1, 15.50, 'Payé'),  -- Paiement pour le colis 1
    (2, 8.25, 'En attente'),  -- Paiement pour le colis 2
    (3, 20.00, 'Payé'),  -- Paiement pour le colis 3
    (4, 12.00, 'Non payé');  -- Paiement pour le colis 4

-- Insertion d'historiques de livraisons
INSERT INTO historique_livraisons (livraison_id, statut)
VALUES
    (1, 'En cours'),  -- Livraison 1 en cours
    (2, 'Complète'),  -- Livraison 2 terminée
    (3, 'Complète'),  -- Livraison 3 terminée
    (4, 'Échouée');  -- Livraison 4 échouée

-- Insertion de factures
INSERT INTO factures (colis_id, montant, statut, date_echeance)
VALUES
    (1, 15.50, 'Payée', '2025-02-10'),  -- Facture pour le colis 1
    (2, 8.25, 'En attente', '2025-02-15'),  -- Facture pour le colis 2
    (3, 20.00, 'Payée', '2025-02-12'),  -- Facture pour le colis 3
    (4, 12.00, 'Non payée', '2025-02-20');  -- Facture pour le colis 4

-- Insertion de statistiques des utilisateurs
INSERT INTO statistiques (utilisateur_id, date, nombre_colis_livrés, montant_total)
VALUES
    (3, '2025-02-05', 2, 35.50),  -- Statistiques pour client1
    (4, '2025-02-06', 1, 8.25);  -- Statistiques pour client2