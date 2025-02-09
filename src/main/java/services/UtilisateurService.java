package services;

import Accèe_SQL.UtilisateurDAO;
import models.Utilisateur;
import java.util.List;

public class UtilisateurService {
    private final UtilisateurDAO utilisateurDAO;

    // Constructeur qui initialise le DAO
    public UtilisateurService() {
        utilisateurDAO = new UtilisateurDAO();
    }

    // Ajouter un utilisateur
    public boolean ajouterUtilisateur(String nom, String email, String motDePasse, String role) {
        if (nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || role.isEmpty()) {
            System.out.println("Tous les champs sont obligatoires.");
            return false;
        }

        Utilisateur utilisateur = new Utilisateur(0, nom, email, motDePasse, role);
        return utilisateurDAO.ajouter(utilisateur);
    }

    // Mettre à jour un utilisateur
    public boolean mettreAJourUtilisateur(int id, String username, String email, String motDePasse, String role) {
        Utilisateur utilisateur = utilisateurDAO.obtenirParId(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !");
            return false;
        }

        utilisateur.setUsername(username);
        utilisateur.setEmail(email);
        utilisateur.setPassword(motDePasse);
        utilisateur.setRole(role);
        return utilisateurDAO.mettreAJour(utilisateur);
    }

    // Supprimer un utilisateur
    public boolean supprimerUtilisateur(int id) {
        return utilisateurDAO.supprimer(id);
    }

    // Récupérer un utilisateur par ID
    public Utilisateur obtenirUtilisateurParId(int id) {
        return utilisateurDAO.obtenirParId(id);
    }

    // Authentifier un utilisateur
    public Utilisateur authentifierUtilisateur(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.authentifier(email, motDePasse);
        if (utilisateur == null) {
            System.out.println("Email ou mot de passe incorrect !");
        }
        return utilisateur;
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurDAO.obtenirTous();
    }
}
