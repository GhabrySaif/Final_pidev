package services;

import models.Colis;
import Accèe_SQL.ColisDAO;
import java.util.List;

public class ColisService {
    private ColisDAO colisDAO;

    // Constructeur
    public ColisService() {
        colisDAO = new ColisDAO();
    }

    // Méthode pour ajouter un nouveau colis
    public boolean ajouterColis(Colis colis) {
        System.out.println("ColisService.ajouterColis appelé");
        if (colis == null) {
            System.err.println("Erreur: Colis est null");
            return false;
        }

        // Validation des données
        if (colis.getUtilisateurId() <= 0) {
            System.err.println("Erreur: ID utilisateur invalide: " + colis.getUtilisateurId());
            return false;
        }

        if (colis.getDescription() == null || colis.getDescription().trim().isEmpty()) {
            System.err.println("Erreur: Description manquante");
            return false;
        }

        if (colis.getAdresseDestination() == null || colis.getAdresseDestination().trim().isEmpty()) {
            System.err.println("Erreur: Adresse de destination manquante");
            return false;
        }

        boolean result = colisDAO.ajouter(colis);
        System.out.println("Résultat ColisService.ajouterColis: " + result);
        return result;
    }

    // Méthode pour mettre à jour un colis existant
    public boolean mettreAJourColis(Colis colis) {
        return colisDAO.mettreAJour(colis);
    }

    // Méthode pour supprimer un colis
    public boolean supprimerColis(int colisId) {
        return colisDAO.supprimer(colisId);
    }

    // Méthode pour obtenir un colis par son ID
    public Colis obtenirColisParId(int colisId) {
        return colisDAO.obtenirParId(colisId);
    }

    // Méthode pour obtenir tous les colis
    public List<Colis> obtenirTousLesColis() {
        return colisDAO.obtenirTous();
    }

    // Méthode pour obtenir les colis d'un utilisateur spécifique
    public List<Colis> obtenirColisParUtilisateur(int utilisateurId) {
        System.out.println("ColisService.obtenirColisParUtilisateur() appelé avec utilisateurId: " + utilisateurId);
        var result = colisDAO.obtenirParUtilisateur(utilisateurId);
        System.out.println("ColisService retourne " + result.size() + " colis pour l'utilisateur " + utilisateurId);
        return result;
    }

    // Méthode pour changer le statut d'un colis
    public boolean changerStatutColis(int colisId, String statut) {
        Colis colis = colisDAO.obtenirParId(colisId);
        if (colis != null) {
            colis.setStatut(statut);
            return colisDAO.mettreAJour(colis);
        }
        return false;
    }

}
