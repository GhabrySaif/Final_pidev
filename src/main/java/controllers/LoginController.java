package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import models.Utilisateur;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class LoginController {

    @FXML
    private TextField usernameField;  // Champ pour le nom d'utilisateur
    @FXML
    private PasswordField passwordField;  // Champ pour le mot de passe
    @FXML
    private Button loginButton;  // Bouton de connexion

    private UtilisateurService utilisateurService;

    public LoginController() {
        utilisateurService = new UtilisateurService();
    }

    // Méthode appelée lors du clic sur le bouton de connexion
    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer votre nom d'utilisateur et votre mot de passe.");
            alert.show();
            return;
        }
        // Vérification des informations d'identification
        Utilisateur utilisateur = utilisateurService.authentifierUtilisateur(username, password);
        if (utilisateur != null) {
            // Rediriger vers l'écran approprié en fonction du rôle
            redirectToAppropriatePage(utilisateur);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentification échouée");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
            alert.show();
        }
    }

    // Méthode pour rediriger l'utilisateur vers la page correspondante en fonction de son rôle
    private void redirectToAppropriatePage(Utilisateur utilisateur) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        System.out.println(utilisateur.getRole());

        String fxmlPath = "";
        switch (utilisateur.getRole()) {
            case "admin" -> fxmlPath = "/admin_dashboard.fxml";
            case "livreur" -> fxmlPath = "/livreur_dashboard.fxml";
//            case "client" -> fxmlPath = "/client_dashboard.fxml";
        }

        // Vérifier si le fichier existe
        URL fxmlUrl = getClass().getResource(fxmlPath);
        if (fxmlUrl == null) {
            System.err.println("FXML file not found: " + fxmlPath);
            return;
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("admin_dashboard.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // Méthode pour afficher une boîte de dialogue d'alerte
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}