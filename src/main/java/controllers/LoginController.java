package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import models.Utilisateur;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField emailField; // Email de l'utilisateur
    @FXML
    private PasswordField passwordField; // Mot de passe
    @FXML
    private Button loginButton; // Bouton de connexion

    private final UtilisateurService utilisateurService = new UtilisateurService();

    // Connexion
    @FXML
    private void handleLogin() throws IOException {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez entrer votre email et votre mot de passe.");
            return;
        }

        Utilisateur utilisateur = utilisateurService.authentifierUtilisateur(email, password);
        if (utilisateur != null) {
            redirectToAppropriatePage(utilisateur);
        } else {
            showAlert(Alert.AlertType.ERROR, "Échec de la connexion", "Email ou mot de passe incorrect.");
        }
    }

    // Rediriger en fonction du rôle
    private void redirectToAppropriatePage(Utilisateur utilisateur) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        String fxmlPath;

        switch (utilisateur.getRole().toLowerCase()) {
            case "admin" -> fxmlPath = "/admin_dashboard.fxml";
            case "livreur" -> fxmlPath = "/livreur_dashboard.fxml";
            case "client" -> fxmlPath = "/client_dashboard.fxml";
            default -> {
                showAlert(Alert.AlertType.ERROR, "Rôle non reconnu", "Rôle: " + utilisateur.getRole());
                return;
            }
        }

        URL fxmlUrl = getClass().getResource(fxmlPath);
        if (fxmlUrl == null) {
            System.err.println("FXML non trouvé: " + fxmlPath);
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(loader.load());

        // Injection utilisateur dans le contrôleur
        String role = utilisateur.getRole().toLowerCase();
        switch (role) {
            case "client" -> loader.<controllers.client.ClientDashboardController>getController().setCurrentUser(utilisateur);
            case "livreur" -> loader.<controllers.livreur.LivreurDashboardController>getController().setCurrentUser(utilisateur);
            // Pas de besoin de setter pour admin ici si non nécessaire
        }

        stage.setScene(scene);
        stage.setTitle("Dashboard - " + utilisateur.getRole());
        stage.show();
    }

    // Aller vers inscription
    @FXML
    private void goToRegister() throws IOException {
        changeScene("/register.fxml", "Inscription");
    }

    // Aller vers mot de passe oublié
    @FXML
    private void goToForgetPassword() throws IOException {
        changeScene("/forget_password.fxml", "Mot de passe oublié");
    }

    private void changeScene(String fxmlPath, String title) throws IOException {
        URL fxmlUrl = getClass().getResource(fxmlPath);
        if (fxmlUrl == null) {
            System.err.println("FXML non trouvé: " + fxmlPath);
            return;
        }

        Parent root = FXMLLoader.load(fxmlUrl);
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.show();
    }
}
