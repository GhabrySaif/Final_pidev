package controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Colis;
//import services.ColisService;

public class GestionColisController {

//    @FXML
//    private ListView<Colis> colisListView;  // Liste des colis à afficher
//    @FXML
//    private TextArea colisDetailsTextArea;  // Zone de texte pour afficher les détails d'un colis
//    @FXML
//    private TextField poidsTextField;  // Champ pour saisir le poids du colis
//    @FXML
//    private TextField dimensionsTextField;  // Champ pour saisir les dimensions du colis
//    @FXML
//    private TextField adresseLivraisonTextField;  // Champ pour saisir l'adresse de livraison
//    @FXML
//    private Button addButton;  // Bouton pour ajouter un colis
//    @FXML
//    private Button updateButton;  // Bouton pour mettre à jour un colis
//    @FXML
//    private Button deleteButton;  // Bouton pour supprimer un colis
//
//    private ColisService colisService;
//    private ObservableList<Colis> colisList;
//
//    public GestionColisController() {
//        colisService = new ColisService();
//    }
//
//    // Méthode appelée lors de l'initialisation de la vue
//    @FXML
//    private void initialize() {
//        colisList = FXCollections.observableArrayList(colisService.obtenirTousLesColis());
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
//                "Adresse de Livraison: " + colis.getAdresseLivraison() + "\n" +
//                "Statut: " + colis.getStatut();
//        colisDetailsTextArea.setText(details);
//    }
//
//    // Ajouter un nouveau colis
//    @FXML
//    private void handleAddColis() {
//        String poids = poidsTextField.getText();
//        String dimensions = dimensionsTextField.getText();
//        String adresseLivraison = adresseLivraisonTextField.getText();
//
//        if (poids.isEmpty() || dimensions.isEmpty() || adresseLivraison.isEmpty()) {
//            showAlert(AlertType.WARNING, "Champs incomplets", "Tous les champs doivent être remplis pour ajouter un colis.");
//            return;
//        }
//
//        // Créer un nouveau colis et l'ajouter
//        Colis colis = new Colis(poids, dimensions, adresseLivraison);
//        boolean added = colisService.ajouterColis(colis);
//
//        if (added) {
//            showAlert(AlertType.INFORMATION, "Colis ajouté", "Le colis a été ajouté avec succès.");
//            refreshColisList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible d'ajouter le colis.");
//        }
//    }
//
//    // Mettre à jour un colis sélectionné
//    @FXML
//    private void handleUpdateColis() {
//        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();
//
//        if (selectedColis == null) {
//            showAlert(AlertType.WARNING, "Aucun colis sélectionné", "Veuillez sélectionner un colis à mettre à jour.");
//            return;
//        }
//
//        String poids = poidsTextField.getText();
//        String dimensions = dimensionsTextField.getText();
//        String adresseLivraison = adresseLivraisonTextField.getText();
//
//        if (poids.isEmpty() || dimensions.isEmpty() || adresseLivraison.isEmpty()) {
//            showAlert(AlertType.WARNING, "Champs incomplets", "Tous les champs doivent être remplis pour mettre à jour le colis.");
//            return;
//        }
//
//        // Mettre à jour le colis
////        selectedColis.setPoids(poids);
//        selectedColis.setAdresseLivraison(adresseLivraison);
//        boolean updated = colisService.mettreAJourColis(selectedColis);
//
//        if (updated) {
//            showAlert(AlertType.INFORMATION, "Colis mis à jour", "Les informations du colis ont été mises à jour.");
//            refreshColisList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour le colis.");
//        }
//    }
//
//    // Supprimer un colis
//    @FXML
//    private void handleDeleteColis() {
//        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();
//
//        if (selectedColis == null) {
//            showAlert(AlertType.WARNING, "Aucun colis sélectionné", "Veuillez sélectionner un colis à supprimer.");
//            return;
//        }
//
//        boolean deleted = colisService.supprimerColis(selectedColis.getId());
//
//        if (deleted) {
//            showAlert(AlertType.INFORMATION, "Colis supprimé", "Le colis a été supprimé avec succès.");
//            refreshColisList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible de supprimer le colis.");
//        }
//    }
//
//    // Rafraîchir la liste des colis
//    private void refreshColisList() {
//        colisList.setAll(colisService.obtenirTousLesColis());
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
