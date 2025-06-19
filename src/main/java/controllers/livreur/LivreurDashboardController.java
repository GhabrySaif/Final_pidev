package controllers.livreur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Livraison;
import models.Utilisateur;
import services.LivraisonService;

import java.io.IOException;

public class LivreurDashboardController {

    @FXML
    private TableView<Livraison> tableViewLivraisons; // Table des livraisons
    @FXML
    private TableColumn<Livraison, Integer> livraisonId;
    @FXML
    private TableColumn<Livraison, Integer> livraisonColisId;
    @FXML
    private TableColumn<Livraison, String> livraisonDate;
    @FXML
    private TableColumn<Livraison, String> livraisonStatut;

    private LivraisonService livraisonService;
    private ObservableList<Livraison> livraisonsList;
    private Utilisateur currentUser; // L'utilisateur livreur connecté

    public LivreurDashboardController() {
        livraisonService = new LivraisonService();
    }

    // Méthode appelée lors de l'initialisation de la vue
    @FXML
    private void initialize() {
        // Configurer les colonnes du tableau
        livraisonId.setCellValueFactory(new PropertyValueFactory<>("id"));
        livraisonColisId.setCellValueFactory(new PropertyValueFactory<>("colisId"));
        livraisonDate.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
        livraisonStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        System.out.println("Colonnes du tableau livreur configurées");

        // Charger les livraisons en cours (sera fait quand setCurrentUser sera appelé)
        // refreshLivraisonsList(); // Ne pas charger maintenant - attendre
        // setCurrentUser()
        System.out.println("LivreurDashboardController.initialize() terminé - en attente de setCurrentUser()");
    }

    // Définir l'utilisateur livreur connecté
    public void setCurrentUser(Utilisateur user) {
        System.out.println("LivreurDashboardController.setCurrentUser() appelé");
        if (user != null) {
            System.out.println("Livreur défini: ID=" + user.getId() + ", Username=" + user.getUsername() + ", Role="
                    + user.getRole());
        } else {
            System.out.println("Utilisateur livreur est null!");
        }
        this.currentUser = user;
        refreshLivraisonsList();
    }

    // Rafraîchir la liste des livraisons
    private void refreshLivraisonsList() {
        try {
            System.out.println("LivreurDashboardController.refreshLivraisonsList() appelé");

            if (currentUser != null) {
                System.out.println("Chargement des livraisons pour le livreur ID: " + currentUser.getId());
                var driverLivraisons = livraisonService.obtenirLivraisonsParLivreur(currentUser.getId());
                livraisonsList = FXCollections.observableArrayList(driverLivraisons);
                System.out.println("Nombre de livraisons trouvées pour ce livreur: " + driverLivraisons.size());

                // Log des livraisons trouvées pour debug
                for (var livraison : driverLivraisons) {
                    System.out.println("  - Livraison ID=" + livraison.getId() +
                            ", ColisID=" + livraison.getColisId() +
                            ", Statut=" + livraison.getStatut() +
                            ", LivreurID=" + livraison.getLivreurId());
                }
            } else {
                System.err.println("ERREUR: Aucun livreur défini! Ne peut pas charger les livraisons.");
                System.err.println("Le livreur dashboard ne devrait jamais être ouvert sans utilisateur connecté.");
                // Initialiser avec une liste vide - NE PAS charger toutes les livraisons
                livraisonsList = FXCollections.observableArrayList();
            }

            tableViewLivraisons.setItems(livraisonsList);
            System.out.println(
                    "Table mise à jour avec " + livraisonsList.size() + " livraisons pour le livreur connecté");
        } catch (Exception e) {
            System.err.println("Erreur lors du rafraîchissement de la liste des livraisons:");
            e.printStackTrace();
            // Initialiser avec une liste vide en cas d'erreur
            livraisonsList = FXCollections.observableArrayList();
            tableViewLivraisons.setItems(livraisonsList);
        }
    }

    // Méthode pour marquer une livraison comme complète
    @FXML
    private void handleMarquerLivraisonComplete() {
        Livraison selectedLivraison = tableViewLivraisons.getSelectionModel().getSelectedItem();

        if (selectedLivraison == null) {
            showAlert(AlertType.WARNING, "Aucune livraison sélectionnée",
                    "Veuillez sélectionner une livraison pour mettre à jour son statut.");
            return;
        }

        // Mettre à jour le statut de la livraison à "Complète"
        boolean updated = livraisonService.changerStatutLivraison(selectedLivraison.getId(), "Complète");

        if (updated) {
            showAlert(AlertType.INFORMATION, "Statut mis à jour",
                    "La livraison a été marquée comme complète avec succès.");
            refreshLivraisonsList(); // Rafraîchir la liste des livraisons
        } else {
            showAlert(AlertType.ERROR, "Erreur de mise à jour",
                    "Il y a eu un problème lors de la mise à jour du statut de la livraison.");
        }
    }

    // Méthodes de navigation
    @FXML
    private void handleVoirLivraisons() {
        refreshLivraisonsList();
    }

    @FXML
    private void handleDeconnexion() {
        try {
            // Retourner à l'écran de connexion
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) tableViewLivraisons.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}