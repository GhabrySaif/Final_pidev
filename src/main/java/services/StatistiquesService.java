//package services;
//
//import dao.ColisDAO;
//import dao.LivraisonDAO;
//import dao.UtilisateurDAO;
//import models.Colis;
//import models.Livraison;
//import models.Utilisateur;
//import java.util.List;
//
//public class StatistiquesService {
//    private ColisDAO colisDAO;
//    private LivraisonDAO livraisonDAO;
//    private UtilisateurDAO utilisateurDAO;
//
//    // Constructeur qui initialise les DAO
//    public StatistiquesService() {
//        colisDAO = new ColisDAO();
//        livraisonDAO = new LivraisonDAO();
//        utilisateurDAO = new UtilisateurDAO();
//    }
//
//    // Nombre total de colis dans le système
//    public int obtenirNombreTotalColis() {
//        List<Colis> colisList = colisDAO.obtenirTous();
//        return colisList.size();
//    }
//
//    // Nombre de colis livrés
//    public int obtenirNombreColisLivres() {
//        List<Colis> colisList = colisDAO.obtenirTous();
//        int count = 0;
//        for (Colis colis : colisList) {
//            if (colis.getStatut().equals("livré")) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    // Nombre de colis en attente de livraison
//    public int obtenirNombreColisEnAttente() {
//        List<Colis> colisList = colisDAO.obtenirTous();
//        int count = 0;
//        for (Colis colis : colisList) {
//            if (colis.getStatut().equals("en attente")) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    // Nombre total de livraisons terminées
//    public int obtenirNombreLivraisonsTerminees() {
//        List<Livraison> livraisonsList = livraisonDAO.obtenirToutes();
//        int count = 0;
//        for (Livraison livraison : livraisonsList) {
//            if (livraison.getStatut().equals("terminée")) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    // Nombre total de livraisons en cours
//    public int obtenirNombreLivraisonsEnCours() {
//        List<Livraison> livraisonsList = livraisonDAO.obtenirToutes();
//        int count = 0;
//        for (Livraison livraison : livraisonsList) {
//            if (livraison.getStatut().equals("en cours")) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    // Nombre total de livreurs
//    public int obtenirNombreLivreurs() {
//        List<Utilisateur> utilisateursList = utilisateurDAO.obtenirTous();
//        int count = 0;
//        for (Utilisateur utilisateur : utilisateursList) {
//            if (utilisateur.getRole().equals("livreur")) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    // Nombre total de clients
//    public int obtenirNombreClients() {
//        List<Utilisateur> utilisateursList = utilisateurDAO.obtenirTous();
//        int count = 0;
//        for (Utilisateur utilisateur : utilisateursList) {
//            if (utilisateur.getRole().equals("client")) {
//                count++;
//            }
//        }
//        return count;
//    }
//}
