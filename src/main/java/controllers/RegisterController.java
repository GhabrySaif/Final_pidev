package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import services.UtilisateurService;

import java.io.IOException;
import java.net.URL;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ChoiceBox<String> roleBox;
    @FXML private Label errorLabel;

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void initialize() {
        roleBox.getItems().addAll("Client", "Livreur");
        roleBox.setValue("Client");
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleBox.getValue();

        // Basic input validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        if (!email.matches("[\\w.-]+@[\\w.-]+\\.\\w+")) {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Format d'email invalide.");
            return;
        }

        boolean success = utilisateurService.ajouterUtilisateur(username, email, password, role);

        if (success) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Inscription réussie !");
            // Optionally, clear fields or redirect to login automatically
        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Erreur: vérifiez les champs ou email déjà utilisé.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        try {
            URL fxmlUrl = getClass().getResource("/login.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML file not found");
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Erreur lors du chargement de la page de connexion.");
        }
    }
}
