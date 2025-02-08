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
import models.Paiement;
import models.Livraison;
//import services.PaiementService;
//import services.LivraisonService;

public class GestionPaiementsController {

//    @FXML
//    private ListView<Paiement> paiementsListView;  // Liste des paiements
//    @FXML
//    private TextArea paiementDetailsTextArea;  // Zone de texte pour afficher les détails du paiement
//    @FXML
//    private TextField statutPaiementTextField;  // Champ pour saisir le statut du paiement
//    @FXML
//    private Button updatePaiementButton;  // Bouton pour mettre à jour le statut du paiement
//    @FXML
//    private Button associerPaiementButton;  // Bouton pour associer un paiement à une livraison
//
//    private PaiementService paiementService;
//    private LivraisonService livraisonService;
//    private ObservableList<Paiement> paiementsList;
//
//    public GestionPaiementsController() {
//        paiementService = new PaiementService();
//        livraisonService = new LivraisonService();
//    }
//
//    // Méthode appelée lors de l'initialisation de la vue
//    @FXML
//    private void initialize() {
//        paiementsList = FXCollections.observableArrayList(paiementService.obtenirTousLesPaiements());
//        paiementsListView.setItems(paiementsList);
//
//        // Sélectionner un paiement et afficher ses détails
//        paiementsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                showPaiementDetails(newValue);
//            }
//        });
//    }
//
//    // Afficher les détails d'un paiement
//    private void showPaiementDetails(Paiement paiement) {
//        String details = "ID Paiement: " + paiement.getId() + "\n" +
//                "Montant: " + paiement.getMontant() + "€\n" +
//                "Statut: " + paiement.getStatut() + "\n" +
//                "Date: " + paiement.getDate() + "\n" +
//                "Livraison ID: " + paiement.getLivraisonId();
//        paiementDetailsTextArea.setText(details);
//    }
//
//    // Mettre à jour le statut du paiement
//    @FXML
//    private void handleUpdatePaiement() {
//        Paiement selectedPaiement = paiementsListView.getSelectionModel().getSelectedItem();
//
//        if (selectedPaiement == null) {
//            showAlert(AlertType.WARNING, "Aucun paiement sélectionné", "Veuillez sélectionner un paiement pour mettre à jour son statut.");
//            return;
//        }
//
//        String statut = statutPaiementTextField.getText();
//
//        if (statut.isEmpty()) {
//            showAlert(AlertType.WARNING, "Statut manquant", "Le statut du paiement ne peut pas être vide.");
//            return;
//        }
//
//        // Mettre à jour le statut du paiement
//        selectedPaiement.setStatut(statut);
//        boolean updated = paiementService.mettreAJourPaiement(selectedPaiement);
//
//        if (updated) {
//            showAlert(AlertType.INFORMATION, "Statut mis à jour", "Le statut du paiement a été mis à jour avec succès.");
//            refreshPaiementsList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour le statut du paiement.");
//        }
//    }
//
//    // Associer un paiement à une livraison
//    @FXML
//    private void handleAssocierPaiement() {
//        Paiement selectedPaiement = paiementsListView.getSelectionModel().getSelectedItem();
//
//        if (selectedPaiement == null) {
//            showAlert(AlertType.WARNING, "Aucun paiement sélectionné", "Veuillez sélectionner un paiement à associer.");
//            return;
//        }
//
//        // Sélectionner la livraison associée
//        Livraison livraison = livraisonService.obtenirLivraisonParId(selectedPaiement.getLivraisonId());
//
//        if (livraison == null) {
//            showAlert(AlertType.WARNING, "Livraison introuvable", "La livraison associée à ce paiement n'a pas été trouvée.");
//            return;
//        }
//
//        // Associer le paiement à la livraison
//        selectedPaiement.setLivraisonId(livraison.getId());
//        boolean associated = paiementService.associerPaiementALivraison(selectedPaiement);
//
//        if (associated) {
//            showAlert(AlertType.INFORMATION, "Paiement associé", "Le paiement a été associé à la livraison avec succès.");
//            refreshPaiementsList();
//        } else {
//            showAlert(AlertType.ERROR, "Erreur", "Impossible d'associer le paiement à la livraison.");
//        }
//    }
//
//    // Rafraîchir la liste des paiements
//    private void refreshPaiementsList() {
//        paiementsList.setAll(paiementService.obtenirTousLesPaiements());
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