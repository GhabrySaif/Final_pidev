package services;

import Accèe_SQL.ColisDAO;
import Accèe_SQL.LivraisonDAO;
import Accèe_SQL.UtilisateurDAO;
import models.Colis;
import models.Livraison;
import models.Utilisateur;
import java.util.List;

public class StatistiquesService {
    private final ColisDAO colisDAO;
    private final LivraisonDAO livraisonDAO;
    private final UtilisateurDAO utilisateurDAO;

    // Constructeur qui initialise les DAO
    public StatistiquesService() {
        colisDAO = new ColisDAO();
        livraisonDAO = new LivraisonDAO();
        utilisateurDAO = new UtilisateurDAO();
    }
    // Nombre total de colis dans le système
    public int obtenirNombreTotalColis() {
        List<Colis> colisList = colisDAO.obtenirTous();
        return colisList.size();
    }

    // Nombre de colis livrés
    public int obtenirNombreColisLivres() {
        List<Colis> colisList = colisDAO.obtenirTous();
        int count = 0;
        for (Colis colis : colisList) {
            if (colis.getStatut().equals("Livré")) {
                count++;
            }
        }
        return count;
    }

    // Nombre de colis en attente de livraison
    public int obtenirNombreColisEnAttente() {
        List<Colis> colisList = colisDAO.obtenirTous();
        int count = 0;
        for (Colis colis : colisList) {
            if (colis.getStatut().equals("En attente")) {
                count++;
            }
        }
        return count;
    }

    // Nombre de colis en transit
    public int obtenirNombreColisEnTransit() {
        List<Colis> colisList = colisDAO.obtenirTous();
        int count = 0;
        for (Colis colis : colisList) {
            if (colis.getStatut().equals("En transit")) {
                count++;
            }
        }
        return count;
    }
    // Nombre total de livraisons terminées
    public int obtenirNombreLivraisonsTerminees() {
        List<Livraison> livraisonsList = livraisonDAO.obtenirToutes();
        int count = 0;
        for (Livraison livraison : livraisonsList) {
            if (livraison.getStatut().equals("Complète")) {
                count++;
            }
        }
        return count;
    }

    // Nombre total de livraisons en cours
    public int obtenirNombreLivraisonsEnCours() {
        List<Livraison> livraisonsList = livraisonDAO.obtenirToutes();
        int count = 0;
        for (Livraison livraison : livraisonsList) {
            if (livraison.getStatut().equals("En cours")) {
                count++;
            }
        }
        return count;
    }

    // Nombre total de livreurs
    public int obtenirNombreLivreurs() {
        List<Utilisateur> utilisateursList = utilisateurDAO.obtenirTous();
        int count = 0;
        for (Utilisateur utilisateur : utilisateursList) {
            if (utilisateur.getRole().equals("Livreur")) {
                count++;
            }
        }
        return count;
    }
    // Nombre total de clients
    public int obtenirNombreClients() {
        List<Utilisateur> utilisateursList = utilisateurDAO.obtenirTous();
        int count = 0;
        for (Utilisateur utilisateur : utilisateursList) {
            if (utilisateur.getRole().equals("Client")) {
                count++;
            }
        }
        return count;
    }

    // Calculer le taux de livraison (pourcentage de colis livrés)
    public double obtenirTauxLivraison() {
        int totalColis = obtenirNombreTotalColis();
        if (totalColis == 0) return 0.0;
        int colisLivres = obtenirNombreColisLivres();
        return (double) colisLivres / totalColis * 100;
    }

    // Obtenir le livreur le plus actif
    public String obtenirLivreurLePlusActif() {
        List<Utilisateur> livreurs = utilisateurDAO.obtenirTous();
        String livreurActif = "Aucun";
        int maxLivraisons = 0;

        for (Utilisateur utilisateur : livreurs) {
            if (utilisateur.getRole().equals("Livreur")) {
                List<Livraison> livraisonsLivreur = livraisonDAO.obtenirParLivreur(utilisateur.getId());
                if (livraisonsLivreur.size() > maxLivraisons) {
                    maxLivraisons = livraisonsLivreur.size();
                    livreurActif = utilisateur.getUsername();
                }
            }
        }
        return livreurActif;
    }
}
