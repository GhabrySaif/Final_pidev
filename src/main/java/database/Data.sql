INSERT INTO utilisateurs (username, password, role, email, phone)
VALUES ('admin', 'admin123', 'Admin', 'admin@example.com', '0123456789'),
       ('livreur1', 'livreur123', 'Livreur', 'livreur1@example.com', '0987654321'),
       ('Client1', 'Client123', 'Client', 'Client1@example.com', '4321098765');

INSERT INTO colis (poids, volume, description, statut, utilisateur_id)
VALUES (2.5, 0.15, 'Petit colis fragile', 'En attente', 3),
       (5.0, 0.30, 'Moyen colis', 'En transit', 3),
       (10.0, 0.50, 'Gros colis', 'Livré', 3);

INSERT INTO livraisons (colis_id, livreur_id, date_livraison, statut)
VALUES (1, 2, NULL, 'En cours'),
       (2, 2, '2025-02-08 14:30:00', 'Complète');

INSERT INTO historique_livraisons (livraison_id, statut)
VALUES (1, 'Pris en charge par le livreur'),
       (2, 'Colis livré avec succès');

INSERT INTO statistiques (utilisateur_id, date, nombre_colis_livrés, montant_total)
VALUES (2, '2025-02-08', 1, 50.00);