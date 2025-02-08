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
import models.Livraison;
//import models.Livreur;
import services.LivraisonService;
//import services.LivreurService;

public class GestionLivraisonsController {

//    @FXML
//    private ListView<Livraison> livraisonsListView;  // Liste des livraisons à afficher
//    @FXML
//    private TextArea livraisonDetailsTextArea;  // Zone de texte pour afficher les détails d'une livraison
//    @FXML
//    private TextField statutLivraisonTextField;  // Champ pour saisir le statut de la livraison
//    @FXML
//    private Button assignerLivraisonButton;  // Bouton pour assigner une livraison à un livreur
//    @FXML
//    private Button updateStatutButton;  // Bouton pour mettre à jour le statut de la livraison
//
//    private LivraisonService livraisonService;
////    private LivreurService livreurService;
//    private ObservableList<Livraison> livraisonsList;
//
//    public GestionLivraisonsController() {
//        livraisonService = new LivraisonService();
////        livreurService = new LivreurService();
//    }
//
//    // Méthode appelée lors de l'initialisation de la vue
//    @FXML
//    private void initialize() {
//        livraisonsList = FXCollections.observableArrayList(livraisonService.obtenirToutesLesLivraisons());
//        livraisonsListView.setItems(livraisonsList);
//
//        // Sélectionner une livraison et afficher ses détails
//        livraisonsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                showLivraisonDetails(newValue);
//            }
//        });
//    }
//
//    // Afficher les détails d'une livraison
//    private void showLivraisonDetails(Livraison livraison) {
//        String details = "ID Livraison: " + livraison.getId() + "\n" +
//                "Colis ID: " + livraison.getColisId() + "\n" +
////                "Livreur: " + livraison.getLivreurNom() + "\n" +
//                "Adresse de Livraison: " + livraison.getAdresseLivraison() + "\n" +
//                "Statut: " + livraison.getStatut() + "\n" +
//                "Date de Livraison: " + livraison.getDateLivraison();
//        livraisonDetailsTextArea.setText(details);
//    }
//
//    // Mettre à jour le statut de la livraison
//    @FXML
//    private void handleUpdateStatut() {
//        Livraison selectedLivraison = livraisonsListView.getSelectionModel().getSelectedItem();
//
//        if (selectedLivraison == null) {
//            showAlert(AlertType.WARNING, "Aucune livraison sélectionnée", "Veuillez sélectionner une livraison pour mettre à jour son statut.");
//            return;
//        }
//
//        String statut = statutLivraisonTextField.getText();
//
//        if (statut.isEmpty()) {
//            showAlert(AlertType.WARNING, "Statut manquant", "Le statut de la livraison ne peut pas être vide.");
//            return;
//        }
//
//        // Mettre à jour le statut de la livraison
//        selectedLivraison.setStatut(statut);
//        boolean updated = livraisonService.mettreAJourLivraison(selectedLivraison);
//
//        if (updated) {
//            showAlert(AlertType.INFORMATION, "Statut mis à jour", "Le statut de la livraison a été mis à jour avec succès.");
//            refreshLivraisonsList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour le statut de la livraison.");
//        }
//    }
//
//    // Assigner une livraison à un livreur
//    @FXML
//    private void handleAssignerLivraison() {
//        Livraison selectedLivraison = livraisonsListView.getSelectionModel().getSelectedItem();
//
//        if (selectedLivraison == null) {
//            showAlert(AlertType.WARNING, "Aucune livraison sélectionnée", "Veuillez sélectionner une livraison à assigner.");
//            return;
//        }
//
//        // Choisir un livreur (ce code peut être ajusté pour afficher un choix de livreurs)
////        Livreur livreur = livreurService.obtenirLivreurDisponible();
//
//        if (livreur == null) {
//            showAlert(AlertType.WARNING, "Livreur indisponible", "Aucun livreur disponible pour cette livraison.");
//            return;
//        }
//
//        // Assigner le livreur à la livraison
//        selectedLivraison.setLivreurNom(livreur.getNom());
//        boolean assigned = livraisonService.assignerLivraisonALivreur(selectedLivraison);
//
//        if (assigned) {
//            showAlert(AlertType.INFORMATION, "Livraison assignée", "La livraison a été assignée à " + livreur.getNom());
//            refreshLivraisonsList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible d'assigner la livraison.");
//        }
//    }
//
//    // Rafraîchir la liste des livraisons
//    private void refreshLivraisonsList() {
//        livraisonsList.setAll(livraisonService.obtenirToutesLesLivraisons());
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
