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

        if (utilisateurService.ajouterUtilisateur(username, email, password, role)) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Inscription réussie !");
        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Erreur: vérifiez les champs ou email déjà utilisé.");
        }
    }

    @FXML
    private void handleBackToLogin() throws IOException {
        URL fxmlUrl = getClass().getResource("/login.fxml");
        if (fxmlUrl != null) {
            Parent root = FXMLLoader.load(fxmlUrl);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        }
    }
}
