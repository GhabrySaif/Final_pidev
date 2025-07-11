package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import models.Utilisateur;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    private final UtilisateurService utilisateurService = new UtilisateurService();

    // Roles should match exactly with what is stored in DB / Utilisateur.role (case insensitive)
    private static final String ROLE_ADMIN = "Admin";
    private static final String ROLE_CLIENT = "Client";
    private static final String ROLE_LIVREUR = "Livreur";

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez entrer votre email et votre mot de passe.");
            return;
        }

        try {
            Utilisateur utilisateur = utilisateurService.authentifierUtilisateur(email, password);

            if (utilisateur != null) {
                redirectToAppropriatePage(utilisateur);
            } else {
                showAlert(Alert.AlertType.ERROR, "Échec de la connexion", "Email ou mot de passe incorrect.");
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite : " + e.getMessage());
        }
    }

    private void redirectToAppropriatePage(Utilisateur utilisateur) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        String role = utilisateur.getRole();

        if (role == null) {
            showAlert(Alert.AlertType.ERROR, "Rôle inconnu", "Aucun rôle défini pour cet utilisateur.");
            return;
        }

        switch (role.toLowerCase()) {
            case "admin":
                loadScene(stage, "/admin_dashboard.fxml");
                break;
            case "client":
                loadClientScene(stage, "/client_dashboard.fxml", utilisateur);
                break;
            case "livreur":
                loadScene(stage, "/livreur_dashboard.fxml");
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Rôle inconnu", "Rôle: " + role);
                return;
        }

        stage.setTitle("Dashboard - " + capitalizeFirstLetter(role));
        stage.show();
    }

    private void loadScene(Stage stage, String fxmlPath) throws IOException {
        URL resource = getClass().getResource(fxmlPath);
        if (resource == null) throw new IOException("FXML non trouvé : " + fxmlPath);

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
    }

    private void loadClientScene(Stage stage, String fxmlPath, Utilisateur utilisateur) throws IOException {
        URL resource = getClass().getResource(fxmlPath);
        if (resource == null) throw new IOException("FXML non trouvé : " + fxmlPath);

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        Object controller = loader.getController();
        if (controller instanceof controllers.client.ClientDashboardController) {
            ((controllers.client.ClientDashboardController) controller).setCurrentUser(utilisateur);
        }

        stage.setScene(new Scene(root));
    }

    @FXML
    private void goToRegister() {
        try {
            changeScene("/register.fxml", "Inscription");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page d'inscription : " + e.getMessage());
        }
    }

    @FXML
    private void goToForgetPassword() {
        try {
            changeScene("/forget_password.fxml", "Mot de passe oublié");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page de récupération : " + e.getMessage());
        }
    }

    private void changeScene(String fxmlPath, String title) throws IOException {
        URL resource = getClass().getResource(fxmlPath);
        if (resource == null) throw new IOException("FXML non trouvé : " + fxmlPath);

        Parent root = FXMLLoader.load(resource);
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
        alert.showAndWait();
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
