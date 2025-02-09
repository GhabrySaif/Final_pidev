package controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button Gestion_Livreurs; // Gestion des Clients

    @FXML
    private Button Gestion_Clients; // Gestion des Clients

    @FXML
    private Button Gestion_Colis; // Gestion des Colis

    @FXML
    private Button Statistiques; // Statistiques

    @FXML
    private Button Deconnexion; // Deconnexion

    @FXML
    private AnchorPane contentPane; // Zone où charger les vues dynamiquement

    @FXML
    private void handleGestionClients(ActionEvent event) {
        chargerVue("/GestionClients.fxml");
    }

    @FXML
    private void handleGestionColis(ActionEvent event) {
        chargerVue("/gestion_colis.fxml");
    }

    @FXML
    private void handleStatistiques(ActionEvent event) {
        chargerVue("/Statistiques.fxml");
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        try {
            // Charger l'interface de connexion
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) Deconnexion.getScene().getWindow(); // Récupérer la fenêtre actuelle
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