package services;

import Accèe_SQL.LivraisonDAO;
import models.Livraison;

import java.util.List;

public class LivraisonService {
    private final LivraisonDAO livraisonDAO;

    // Constructeur qui initialise le DAO
    public LivraisonService() {
        livraisonDAO = new LivraisonDAO();
    }

    // Ajouter une livraison (objet Livraison complet)
    public boolean ajouterLivraison(Livraison livraison) {
        System.out.println("LivraisonService.ajouterLivraison() appelé");
        System.out.println("  - ColisID: " + livraison.getColisId());
        System.out.println("  - LivreurID: " + livraison.getLivreurId());
        System.out.println("  - Statut: " + livraison.getStatut());

        boolean result = livraisonDAO.ajouter(livraison);
        System.out.println("  - Résultat: " + (result ? "Succès" : "Échec"));
        return result;
    }

    // Obtenir les livraisons d'un livreur spécifique
    public List<Livraison> obtenirLivraisonsParLivreur(int livreurId) {
        return livraisonDAO.obtenirParLivreur(livreurId);
    }

    // Changer le statut d'une livraison
    public boolean changerStatutLivraison(int livraisonId, String statut) {
        return livraisonDAO.changerStatut(livraisonId, statut);
    }
}
