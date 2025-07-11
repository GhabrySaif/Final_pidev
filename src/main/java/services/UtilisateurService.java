package services;

import Accèe_SQL.UtilisateurDAO;
import models.Utilisateur;

import java.util.List;

public class UtilisateurService {
    private final UtilisateurDAO utilisateurDAO;

    // Constructeur
    public UtilisateurService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    // Ajouter un utilisateur (inscription)
    public boolean ajouterUtilisateur(String nom, String email, String motDePasse, String role) {
        System.out.println("UtilisateurService.ajouterUtilisateur appelé avec:");
        System.out.println("  Nom: '" + nom + "'");
        System.out.println("  Email: '" + email + "'");
        System.out.println("  Mot de passe: '" + motDePasse + "'");
        System.out.println("  Rôle: '" + role + "'");

        if (nom == null || nom.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                motDePasse == null || motDePasse.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
            System.err.println("Erreur: Tous les champs sont obligatoires.");
            return false;
        }

        // Vérifier si le nom d'utilisateur ou email existe déjà
        if (utilisateurDAO.usernameExists(nom.trim())) {
            System.err.println("Erreur: Le nom d'utilisateur '" + nom.trim() + "' existe déjà.");
            return false;
        }

        if (utilisateurDAO.emailExists(email.trim())) {
            System.err.println("Erreur: L'email '" + email.trim() + "' est déjà utilisé.");
            return false;
        }

        Utilisateur utilisateur = new Utilisateur(nom.trim(), email.trim(), motDePasse.trim(), role.trim());
        boolean result = utilisateurDAO.ajouter(utilisateur);
        System.out.println("Résultat de l'ajout: " + result);
        return result;
    }

    // Mettre à jour un utilisateur
    public boolean mettreAJourUtilisateur(int id, String username, String email, String motDePasse, String role) {
        Utilisateur utilisateur = utilisateurDAO.obtenirParId(id);
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !");
            return false;
        }

        utilisateur.setUsername(username.trim());
        utilisateur.setEmail(email.trim());
        utilisateur.setPassword(motDePasse.trim());
        utilisateur.setRole(role.trim());

        return utilisateurDAO.mettreAJour(utilisateur);
    }

    // Supprimer un utilisateur
    public boolean supprimerUtilisateur(int id) {
        return utilisateurDAO.supprimer(id);
    }

    // Obtenir un utilisateur par ID
    public Utilisateur obtenirUtilisateurParId(int id) {
        return utilisateurDAO.obtenirParId(id);
    }

    // Authentifier un utilisateur (connexion)
    public Utilisateur authentifierUtilisateur(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.authentifier(email.trim(), motDePasse.trim());
        if (utilisateur == null) {
            System.out.println("Email ou mot de passe incorrect !");
        }
        return utilisateur;
    }

    // Obtenir tous les utilisateurs
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurDAO.obtenirTous();
    }

    // Obtenir les clients uniquement
    public List<Utilisateur> obtenirClients() {
        return utilisateurDAO.obtenirParRole("Client");
    }

    // Obtenir les livreurs uniquement
    public List<Utilisateur> obtenirLivreurs() {
        return utilisateurDAO.obtenirParRole("Livreur");
    }

    // Obtenir les administrateurs uniquement
    public List<Utilisateur> obtenirAdministrateurs() {
        return utilisateurDAO.obtenirParRole("Admin");
    }
}
