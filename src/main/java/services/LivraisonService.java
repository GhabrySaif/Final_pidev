package services;

import Accèe_SQL.LivraisonDAO;
import models.Livraison;

import java.util.List;

public class LivraisonService {
//    private LivraisonDAO livraisonDAO;
//
//    // Constructeur qui initialise le DAO
//    public LivraisonService() {
//        livraisonDAO = new LivraisonDAO();
//    }
//
//    // Créer une nouvelle livraison
//    public boolean creerLivraison(int colisId, int livreurId, String adresseLivraison, String statut) {
//        Livraison livraison = new Livraison();
//        livraison.setColisId(colisId);
//        livraison.setLivreurId(livreurId);
////        livraison.setAdresseLivraison(adresseLivraison);
//        livraison.setStatut(statut);  // Ex : "en cours", "terminée"
//        return livraisonDAO.ajouter(livraison);
//    }
//
//    // Mettre à jour une livraison
//    public boolean mettreAJourLivraison(int id, int colisId, int livreurId, String adresseLivraison, String statut) {
//        Livraison livraison = livraisonDAO.obtenirParId(id);
//        if (livraison == null) {
//            System.out.println("Livraison non trouvée !");
//            return false;
//        }
//
//        livraison.setColisId(colisId);
//        livraison.setLivreurId(livreurId);
//        livraison.setAdresseLivraison(adresseLivraison);
//        livraison.setStatut(statut);
//        return livraisonDAO.mettreAJour(livraison);
//    }
//
//    // Supprimer une livraison
//    public boolean supprimerLivraison(int id) {
//        return livraisonDAO.supprimer(id);
//    }
//
//    // Obtenir une livraison par son ID
//    public Livraison obtenirLivraisonParId(int id) {
//        return livraisonDAO.obtenirParId(id);
//    }
//
//    // Obtenir toutes les livraisons
//    public List<Livraison> obtenirToutesLesLivraisons() {
//        return livraisonDAO.obtenirTous();
//    }
//
//    // Lister les livraisons en cours
//    public List<Livraison> obtenirLivraisonsEnCours() {
//        List<Livraison> livraisonsList = livraisonDAO.obtenirTous();
//        livraisonsList.removeIf(livraison -> !livraison.getStatut().equals("en cours"));
//        return livraisonsList;
//    }
//
//    // Lister les livraisons terminées
//    public List<Livraison> obtenirLivraisonsTerminees() {
//        List<Livraison> livraisonsList = livraisonDAO.obtenirTous();
//        livraisonsList.removeIf(livraison -> !livraison.getStatut().equals("terminée"));
//        return livraisonsList;
//    }
//
//    // Assigner une livraison à un livreur
//    public boolean assignerLivraisonALivreur(int livraisonId, int livreurId) {
//        Livraison livraison = livraisonDAO.obtenirParId(livraisonId);
//        if (livraison == null) {
//            System.out.println("Livraison non trouvée !");
//            return false;
//        }
//        livraison.setLivreurId(livreurId);
//        return livraisonDAO.mettreAJour(livraison);
//    }
}
