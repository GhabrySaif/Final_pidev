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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Colis;
import models.Utilisateur;
import services.ColisService;
import services.UtilisateurService;
import services.LivraisonService;
import models.Livraison;

public class GestionColisController {

    @FXML
    private ListView<Colis> colisListView; // Liste des colis à afficher
    @FXML
    private TextArea colisDetailsTextArea; // Zone de texte pour afficher les détails d'un colis
    @FXML
    private TextField statutTextField; // Champ pour afficher le statut actuel
    @FXML
    private ComboBox<String> nouveauStatutComboBox; // ComboBox pour choisir le nouveau statut
    @FXML
    private ComboBox<Utilisateur> livreurComboBox; // ComboBox pour assigner un livreur
    @FXML
    private Button updateStatusButton; // Bouton pour mettre à jour le statut
    @FXML
    private Button assignDriverButton; // Bouton pour assigner un livreur
    @FXML
    private Button deleteButton; // Bouton pour supprimer un colis
    @FXML
    private Label enAttenteLabel; // Label pour afficher le nombre de colis en attente
    @FXML
    private Label enTransitLabel; // Label pour afficher le nombre de colis en transit
    @FXML
    private Label livresLabel; // Label pour afficher le nombre de colis livrés

    private ColisService colisService;
    private UtilisateurService utilisateurService;
    private LivraisonService livraisonService;
    private ObservableList<Colis> colisList;

    public GestionColisController() {
        colisService = new ColisService();
        utilisateurService = new UtilisateurService();
        livraisonService = new LivraisonService();
    }

    // Méthode appelée lors de l'initialisation de la vue
    @FXML
    private void initialize() {
        try {
            System.out.println("GestionColisController.initialize() - Début de l'initialisation");

            // Charger la liste des colis
            colisList = FXCollections.observableArrayList();
            var allColis = colisService.obtenirTousLesColis();
            colisList.addAll(allColis);
            colisListView.setItems(colisList);
            System.out.println("Nombre de colis chargés: " + allColis.size());

            // Initialiser les ComboBox
            nouveauStatutComboBox.setItems(FXCollections.observableArrayList("En attente", "En transit", "Livré"));
            System.out.println("ComboBox statut initialisé");

            loadDrivers();
            System.out.println("Livreurs chargés");

            // Sélectionner un colis et afficher ses détails
            colisListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    showColisDetails(newValue);
                    populateManagementFields(newValue);
                }
            });

            // Mettre à jour les statistiques
            updateStatistics();
            System.out.println("Statistiques mises à jour");

            System.out.println("GestionColisController.initialize() - Initialisation terminée avec succès");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation de GestionColisController:");
            e.printStackTrace();

            // Initialiser avec des valeurs par défaut en cas d'erreur
            colisList = FXCollections.observableArrayList();
            colisListView.setItems(colisList);
            nouveauStatutComboBox.setItems(FXCollections.observableArrayList("En attente", "En transit", "Livré"));
        }
    }

    // Afficher les détails d'un colis
    private void showColisDetails(Colis colis) {
        String details = "ID Colis: " + colis.getId() + "\n" +
                "Description: " + colis.getDescription() + "\n" +
                "Poids: " + colis.getPoids() + " kg\n" +
                "Volume: " + colis.getVolume() + " m³\n" +
                "Adresse: " + colis.getAdresseDestination() + "\n" +
                "Statut: " + colis.getStatut() + "\n" +
                "Utilisateur ID: " + colis.getUtilisateurId();
        colisDetailsTextArea.setText(details);
    }

    // Remplir les champs de gestion avec les données du colis sélectionné
    private void populateManagementFields(Colis colis) {
        statutTextField.setText(colis.getStatut());
        nouveauStatutComboBox.setValue(colis.getStatut());
    }

    // Charger la liste des livreurs dans le ComboBox
    private void loadDrivers() {
        try {
            var drivers = utilisateurService.obtenirLivreurs();
            livreurComboBox.setItems(FXCollections.observableArrayList(drivers));
            System.out.println("Nombre de livreurs chargés: " + drivers.size());

            // Configurer l'affichage des livreurs
            livreurComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<Utilisateur>() {
                @Override
                protected void updateItem(Utilisateur item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getUsername() + " (" + item.getEmail() + ")");
                    }
                }
            });

            livreurComboBox.setButtonCell(new javafx.scene.control.ListCell<Utilisateur>() {
                @Override
                protected void updateItem(Utilisateur item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText("Sélectionner un livreur");
                    } else {
                        setText(item.getUsername());
                    }
                }
            });
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des livreurs:");
            e.printStackTrace();
            // Initialiser avec une liste vide en cas d'erreur
            livreurComboBox.setItems(FXCollections.observableArrayList());
        }
    }

    // Mettre à jour le statut d'un colis
    @FXML
    private void handleUpdateStatus() {
        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();
        String newStatus = nouveauStatutComboBox.getValue();

        if (selectedColis == null) {
            showAlert(AlertType.WARNING, "Aucun colis sélectionné",
                    "Veuillez sélectionner un colis pour mettre à jour son statut.");
            return;
        }

        if (newStatus == null || newStatus.isEmpty()) {
            showAlert(AlertType.WARNING, "Statut non sélectionné",
                    "Veuillez sélectionner un nouveau statut.");
            return;
        }

        // Mettre à jour le statut
        selectedColis.setStatut(newStatus);
        boolean updated = colisService.mettreAJourColis(selectedColis);

        if (updated) {
            showAlert(AlertType.INFORMATION, "Statut mis à jour",
                    "Le statut du colis a été mis à jour vers: " + newStatus);
            refreshColisList();
            updateStatistics();
            populateManagementFields(selectedColis);
        } else {
            showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour le statut du colis.");
        }
    }

    // Assigner un livreur à un colis
    @FXML
    private void handleAssignDriver() {
        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();
        Utilisateur selectedDriver = livreurComboBox.getValue();

        if (selectedColis == null) {
            showAlert(AlertType.WARNING, "Aucun colis sélectionné",
                    "Veuillez sélectionner un colis pour assigner un livreur.");
            return;
        }

        if (selectedDriver == null) {
            showAlert(AlertType.WARNING, "Aucun livreur sélectionné",
                    "Veuillez sélectionner un livreur à assigner.");
            return;
        }

        try {
            System.out.println(
                    "Assignation du colis ID=" + selectedColis.getId() + " au livreur ID=" + selectedDriver.getId());

            // 1. Mettre à jour le statut du colis
            selectedColis.setStatut("En transit");
            boolean colisUpdated = colisService.mettreAJourColis(selectedColis);

            if (!colisUpdated) {
                showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour le statut du colis.");
                return;
            }

            // 2. Créer une livraison pour le livreur
            Livraison nouvelleLivraison = new Livraison();
            nouvelleLivraison.setColisId(selectedColis.getId());
            nouvelleLivraison.setLivreurId(selectedDriver.getId());
            nouvelleLivraison.setStatut("En cours");
            // Date de livraison sera null jusqu'à ce que la livraison soit complétée

            System.out.println("Création de la livraison:");
            System.out.println("  - ColisID: " + nouvelleLivraison.getColisId());
            System.out.println("  - LivreurID: " + nouvelleLivraison.getLivreurId());
            System.out.println("  - Statut: " + nouvelleLivraison.getStatut());

            boolean livraisonCreated = livraisonService.ajouterLivraison(nouvelleLivraison);

            if (livraisonCreated) {
                showAlert(AlertType.INFORMATION, "Livreur assigné",
                        "Le colis a été assigné à " + selectedDriver.getUsername() +
                                " et apparaîtra dans son tableau de bord.");
                refreshColisList();
                updateStatistics();
                populateManagementFields(selectedColis);
                System.out.println("Assignation réussie - livraison créée");
            } else {
                // Rollback: remettre le colis en attente si la livraison n'a pas pu être créée
                selectedColis.setStatut("En attente");
                colisService.mettreAJourColis(selectedColis);
                showAlert(AlertType.ERROR, "Erreur", "Impossible de créer la livraison pour le livreur.");
                System.err.println("Échec de la création de la livraison - rollback effectué");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'assignation du livreur:");
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'assignation: " + e.getMessage());
        }
    }

    // Mettre à jour les statistiques
    private void updateStatistics() {
        var allColis = colisService.obtenirTousLesColis();

        long enAttente = allColis.stream().filter(c -> "En attente".equals(c.getStatut())).count();
        long enTransit = allColis.stream().filter(c -> "En transit".equals(c.getStatut())).count();
        long livres = allColis.stream().filter(c -> "Livré".equals(c.getStatut())).count();

        enAttenteLabel.setText(String.valueOf(enAttente));
        enTransitLabel.setText(String.valueOf(enTransit));
        livresLabel.setText(String.valueOf(livres));
    }

    // Supprimer un colis
    @FXML
    private void handleDeleteColis() {
        Colis selectedColis = colisListView.getSelectionModel().getSelectedItem();

        if (selectedColis == null) {
            showAlert(AlertType.WARNING, "Aucun colis sélectionné", "Veuillez sélectionner un colis à supprimer.");
            return;
        }

        boolean deleted = colisService.supprimerColis(selectedColis.getId());

        if (deleted) {
            showAlert(AlertType.INFORMATION, "Colis supprimé", "Le colis a été supprimé avec succès.");
            refreshColisList();
        } else {
            showAlert(AlertType.ERROR, "Erreur", "Impossible de supprimer le colis.");
        }
    }

    // Rafraîchir la liste des colis
    private void refreshColisList() {
        colisList.setAll(colisService.obtenirTousLesColis());
    }

    // Méthode pour afficher une alerte
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
