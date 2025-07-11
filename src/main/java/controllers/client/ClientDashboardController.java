package controllers.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Utilisateur;

import java.io.IOException;

public class ClientDashboardController {

    @FXML
    private Button mesColisBtn;

    @FXML
    private Button ajouterColisBtn;

    @FXML
    private Button suivreColisBtn;

    @FXML
    private Button profilBtn;

    @FXML
    private Button deconnexionBtn;

    @FXML
    private AnchorPane contentPane;

    private Utilisateur currentUser;

    public void setCurrentUser(Utilisateur utilisateur) {
        this.currentUser = utilisateur;
        System.out.println("Utilisateur connecté : " + utilisateur.getEmail());
        // Optional: update UI components to display username, etc.
    }

    @FXML
    private void handleMesColis(ActionEvent event) {
        chargerVue("/mes_colis.fxml");
    }

    @FXML
    private void handleAjouterColis(ActionEvent event) {
        chargerVue("/ajouter_colis.fxml");
    }

    @FXML
    private void handleSuivreColis(ActionEvent event) {
        chargerVue("/suivre_colis.fxml");
    }

    @FXML
    private void handleMonProfil(ActionEvent event) {
        chargerVue("/mon_profil.fxml");
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) deconnexionBtn.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur lors de la déconnexion.");
        }
    }

    private void chargerVue(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Impossible de charger la vue : " + fxmlPath);
        }
    }

    private void showError(String message) {
        System.err.println("Erreur: " + message);
        // Optional: add Alert dialog here if desired
    }
}
