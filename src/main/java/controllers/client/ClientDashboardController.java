package controllers.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Colis;
//import services.ColisService;
//
public class ClientDashboardController {
//
//    @FXML
//    private ListView<Colis> colisListView;  // Liste des colis du client
//    @FXML
//    private TextArea colisDetailsTextArea;  // Zone de texte pour afficher les détails du colis
//    @FXML
//    private Button trackButton;  // Bouton pour suivre un colis
//
//    private ColisService colisService;
//    private ObservableList<Colis> colisList;
//
//    public ClientDashboardController() {
//        colisService = new ColisService();
//    }
//
//    // Méthode appelée lors de l'initialisation de la vue
//    @FXML
//    private void initialize() {
//        // Récupérer la liste des colis du client (par exemple, en fonction de l'utilisateur connecté)
//        colisList = FXCollections.observableArrayList(colisService.obtenirColisParId(1));  // Exemple : 1 est l'ID du client
//        colisListView.setItems(colisList);
//
//        // Sélectionner un colis et afficher ses détails
//        colisListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                showColisDetails(newValue);
//            }
//        });
//    }
//
//    // Afficher les détails d'un colis
//    private void showColisDetails(Colis colis) {
//        String details = "ID Colis: " + colis.getId() + "\n" +
//                "Poids: " + colis.getPoids() + " kg\n" +
//                "Statut: " + colis.getStatut() + "\n" +
//                "Adresse de livraison: " + colis.getAdresseLivraison();
//        colisDetailsTextArea.setText(details);
//    }
//
//    // Méthode pour suivre un colis
//    @FXML
//    private void handleTrackColis() {
//        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();
//
//        if (selectedColis == null) {
//            showAlert(AlertType.WARNING, "Aucun colis sélectionné", "Veuillez sélectionner un colis pour suivre sa livraison.");
//            return;
//        }
//
//        // Consulter le statut actuel de la livraison du colis
//        String statut = colisService.consulterStatutLivraison(selectedColis.getId());
//
//        if (statut != null) {
//            showAlert(AlertType.INFORMATION, "Statut du colis", "Le statut de votre colis est : " + statut);
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible de récupérer le statut du colis.");
//        }
//    }
//
//    // Méthode pour afficher une alerte
//    private void showAlert(AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}