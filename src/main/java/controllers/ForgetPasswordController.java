package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class ForgetPasswordController {

    @FXML private TextField emailField;
    @FXML private Label messageLabel;

    @FXML
    private void handleResetPassword() {
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Veuillez entrer votre email.");
        } else {
            // Simulation d'envoi
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Email de réinitialisation envoyé (simulation).");
        }
    }

    @FXML
    private void handleBackToLogin() throws IOException {
        URL fxmlUrl = getClass().getResource("/login.fxml");
        if (fxmlUrl != null) {
            Parent root = FXMLLoader.load(fxmlUrl);
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        }
    }
}
