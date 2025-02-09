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
        return colisDAO.ajouter(colis);
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
//    public List<Colis> obtenirColisParUtilisateur(int utilisateurId) {
//        return colisDAO.obtenirParUtilisateur(utilisateurId);
//    }

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
