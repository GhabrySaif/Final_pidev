package controllers.livreur;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
//import javafx.stage.Stage;
//import models.Livraison;
//import services.LivraisonService;
//
public class LivreurDashboardController {
//
//    @FXML
//    private ListView<Livraison> livraisonListView;  // Liste des livraisons en cours
//    @FXML
//    private TextArea livraisonDetailsTextArea;  // Zone de texte pour afficher les détails de la livraison
//    @FXML
//    private Button updateStatusButton;  // Bouton pour mettre à jour le statut de la livraison
//
//    private LivraisonService livraisonService;
//    private ObservableList<Livraison> livraisonsList;
//
//    public LivreurDashboardController() {
//        livraisonService = new LivraisonService();
//    }
//
//    // Méthode appelée lors de l'initialisation de la vue
//    @FXML
//    private void initialize() {
//        livraisonsList = FXCollections.observableArrayList(livraisonService.obtenirLivraisonsEnCours());
//        livraisonListView.setItems(livraisonsList);
//
//        // Sélectionner une livraison et afficher ses détails
//        livraisonListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
//                "Livreur ID: " + livraison.getLivreurId() + "\n" +
//                "Adresse de Livraison: " + livraison.getAdresseLivraison() + "\n" +
//                "Statut: " + livraison.getStatut();
//        livraisonDetailsTextArea.setText(details);
//    }
//
//    // Méthode pour mettre à jour le statut de la livraison
//    @FXML
//    private void handleUpdateStatus() {
//        Livraison selectedLivraison = livraisonListView.getSelectionModel().getSelectedItem();
//
//        if (selectedLivraison == null) {
//            showAlert(AlertType.WARNING, "Aucune livraison sélectionnée", "Veuillez sélectionner une livraison pour mettre à jour son statut.");
//            return;
//        }
//
//        // Mettre à jour le statut de la livraison
//        boolean updated = livraisonService.mettreAJourLivraison(selectedLivraison.getId(),
//                selectedLivraison.getColisId(),
//                selectedLivraison.getLivreurId(),
//                selectedLivraison.getAdresseLivraison(),
//                "terminée"); // Exemple de mise à jour du statut à "terminée"
//
//        if (updated) {
//            showAlert(AlertType.INFORMATION, "Statut mis à jour", "Le statut de la livraison a été mis à jour avec succès.");
//            refreshLivraisonsList();  // Rafraîchir la liste des livraisons
//        } else {
//            showAlert(AlertType.ERROR, "Erreur de mise à jour", "Il y a eu un problème lors de la mise à jour du statut de la livraison.");
//        }
//    }
//
//    // Rafraîchir la liste des livraisons en cours
//    private void refreshLivraisonsList() {
//        livraisonsList.setAll(livraisonService.obtenirLivraisonsEnCours());
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