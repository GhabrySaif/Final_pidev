package controllers.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Colis;
import models.Utilisateur;
import services.ColisService;

import java.io.IOException;

public class ClientDashboardController {

    @FXML
    private TableView<Colis> tableViewColis;
    @FXML
    private TableColumn<Colis, Integer> colisId;
    @FXML
    private TableColumn<Colis, String> colisDescription;
    @FXML
    private TableColumn<Colis, Double> colisPoids;
    @FXML
    private TableColumn<Colis, String> colisStatut;
    @FXML
    private TableColumn<Colis, String> colisDestination;

    // Formulaire pour nouveau colis
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField poidsTextField;
    @FXML
    private TextField volumeTextField;
    @FXML
    private TextField destinationTextField;
    @FXML
    private TextArea colisDetailsTextArea;

    // Boutons
    @FXML
    private Button ajouterColisButton;
    @FXML
    private Button suivreColisButton;
    @FXML
    private Button deconnexionButton;

    private ColisService colisService;
    private ObservableList<Colis> colisList;
    private Utilisateur currentUser; // L'utilisateur connecté

    public ClientDashboardController() {
        colisService = new ColisService();
    }

    @FXML
    private void initialize() {
        try {
            System.out.println("ClientDashboardController.initialize() - Début de l'initialisation");

            // Configurer les colonnes du tableau
            colisId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colisDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colisPoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
            colisStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            colisDestination.setCellValueFactory(new PropertyValueFactory<>("adresseDestination"));
            System.out.println("Colonnes du tableau configurées");

            // Charger les colis du client (sera fait quand setCurrentUser sera appelé)
            // refreshColisList(); // Ne pas charger maintenant - attendre setCurrentUser()
            System.out.println("Initialisation terminée - en attente de setCurrentUser()");

            // Listener pour afficher les détails du colis sélectionné
            tableViewColis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    showColisDetails(newValue);
                }
            });

            System.out.println("ClientDashboardController.initialize() - Initialisation terminée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation de ClientDashboardController:");
            e.printStackTrace();
        }
    }

    // Définir l'utilisateur connecté
    public void setCurrentUser(Utilisateur user) {
        System.out.println("ClientDashboardController.setCurrentUser() appelé");
        if (user != null) {
            System.out.println("Utilisateur défini: ID=" + user.getId() + ", Username=" + user.getUsername() + ", Role="
                    + user.getRole());
        } else {
            System.out.println("Utilisateur est null!");
        }
        this.currentUser = user;
        refreshColisList();
    }

    // Rafraîchir la liste des colis du client
    private void refreshColisList() {
        try {
            System.out.println("ClientDashboardController.refreshColisList() appelé");

            if (currentUser != null) {
                System.out.println("Chargement des colis pour l'utilisateur ID: " + currentUser.getId());
                var userColis = colisService.obtenirColisParUtilisateur(currentUser.getId());
                colisList = FXCollections.observableArrayList(userColis);
                System.out.println("Nombre de colis trouvés pour cet utilisateur: " + userColis.size());

                // Log des colis trouvés pour debug
                for (var colis : userColis) {
                    System.out.println("  - Colis ID=" + colis.getId() + ", Description=" + colis.getDescription() +
                            ", Statut=" + colis.getStatut() + ", UtilisateurID=" + colis.getUtilisateurId());
                }
            } else {
                System.err.println("ERREUR: Aucun utilisateur défini! Ne peut pas charger les colis.");
                System.err.println("Le client dashboard ne devrait jamais être ouvert sans utilisateur connecté.");
                // Initialiser avec une liste vide - NE PAS charger tous les colis
                colisList = FXCollections.observableArrayList();
            }

            tableViewColis.setItems(colisList);
            System.out.println("Table mise à jour avec " + colisList.size() + " colis pour l'utilisateur connecté");
        } catch (Exception e) {
            System.err.println("Erreur lors du rafraîchissement de la liste des colis:");
            e.printStackTrace();
            // Initialiser avec une liste vide en cas d'erreur
            colisList = FXCollections.observableArrayList();
            tableViewColis.setItems(colisList);
        }
    }

    // Afficher les détails d'un colis
    private void showColisDetails(Colis colis) {
        String details = "ID: " + colis.getId() + "\n" +
                "Description: " + colis.getDescription() + "\n" +
                "Poids: " + colis.getPoids() + " kg\n" +
                "Volume: " + colis.getVolume() + " m³\n" +
                "Destination: " + colis.getAdresseDestination() + "\n" +
                "Statut: " + colis.getStatut() + "\n" +
                "Date de création: " + colis.getDateCreation();
        colisDetailsTextArea.setText(details);
    }

    // Ajouter un nouveau colis
    @FXML
    private void handleAjouterColis() {
        // Vérification de sécurité - l'utilisateur doit être connecté
        if (currentUser == null) {
            showAlert(AlertType.ERROR, "Erreur de session", "Session utilisateur expirée. Veuillez vous reconnecter.");
            return;
        }

        String description = descriptionTextField.getText();
        String poidsStr = poidsTextField.getText();
        String volumeStr = volumeTextField.getText();
        String destination = destinationTextField.getText();

        if (description.isEmpty() || poidsStr.isEmpty() || volumeStr.isEmpty() || destination.isEmpty()) {
            showAlert(AlertType.WARNING, "Champs incomplets", "Tous les champs doivent être remplis.");
            return;
        }

        try {
            double poids = Double.parseDouble(poidsStr);
            double volume = Double.parseDouble(volumeStr);

            System.out.println("Création d'un nouveau colis pour l'utilisateur: " + currentUser.getUsername() + " (ID="
                    + currentUser.getId() + ")");

            Colis nouveauColis = new Colis();
            // SÉCURITÉ: Toujours assigner le colis à l'utilisateur connecté
            nouveauColis.setUtilisateurId(currentUser.getId());
            nouveauColis.setDescription(description);
            nouveauColis.setPoids(poids);
            nouveauColis.setVolume(volume);
            nouveauColis.setAdresseDestination(destination);
            nouveauColis.setStatut("En attente");

            System.out.println("Détails du nouveau colis:");
            System.out.println("  - UtilisateurID: " + nouveauColis.getUtilisateurId());
            System.out.println("  - Description: " + nouveauColis.getDescription());
            System.out.println("  - Poids: " + nouveauColis.getPoids());
            System.out.println("  - Destination: " + nouveauColis.getAdresseDestination());

            boolean added = colisService.ajouterColis(nouveauColis);

            if (added) {
                showAlert(AlertType.INFORMATION, "Colis ajouté", "Votre colis a été ajouté avec succès.");
                clearForm();
                refreshColisList(); // Recharger SEULEMENT les colis de cet utilisateur
                System.out.println("Colis ajouté avec succès et liste rafraîchie");
            } else {
                showAlert(AlertType.ERROR, "Erreur", "Impossible d'ajouter le colis.");
                System.err.println("Échec de l'ajout du colis");
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Erreur de format", "Le poids et le volume doivent être des nombres valides.");
            System.err.println("Erreur de format numérique: " + e.getMessage());
        }
    }

    // Suivre un colis (afficher ses détails)
    @FXML
    private void handleSuivreColis() {
        Colis selectedColis = tableViewColis.getSelectionModel().getSelectedItem();
        if (selectedColis == null) {
            showAlert(AlertType.WARNING, "Aucun colis sélectionné", "Veuillez sélectionner un colis à suivre.");
            return;
        }
        showColisDetails(selectedColis);
    }

    // Déconnexion
    @FXML
    private void handleDeconnexion() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Stage stage = (Stage) deconnexionButton.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vider le formulaire
    private void clearForm() {
        descriptionTextField.clear();
        poidsTextField.clear();
        volumeTextField.clear();
        destinationTextField.clear();
    }

    // Afficher une alerte
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
