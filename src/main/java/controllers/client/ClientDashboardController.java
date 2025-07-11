package controllers.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import models.Utilisateur;

import java.io.IOException;

public class ClientDashboardController {

    private Utilisateur currentUser;

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

    public void setCurrentUser(Utilisateur utilisateur) {
        this.currentUser = utilisateur;
        System.out.println("Utilisateur connecté : " + utilisateur.getEmail());
    }

    @FXML
    private void handleMesColis(ActionEvent event) {
        chargerVue("/client/MesColis.fxml");
    }

    @FXML
    private void handleAjouterColis(ActionEvent event) {
        chargerVue("/client/AjouterColis.fxml");
    }

    @FXML
    private void handleSuivreColis(ActionEvent event) {
        chargerVue("/client/SuivreColis.fxml");
    }

    @FXML
    private void handleMonProfil(ActionEvent event) {
        chargerVue("/client/MonProfil.fxml");
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) deconnexionBtn.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerVue(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
