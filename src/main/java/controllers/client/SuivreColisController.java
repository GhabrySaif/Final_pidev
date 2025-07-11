package controllers.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Colis;
import models.HistoriqueSuivi;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SuivreColisController {

    @FXML private TextField numeroSuiviField;
    @FXML private Button rechercherButton;

    @FXML private VBox resultatContainer;

    @FXML private Label numeroLabel;
    @FXML private Label destinataireLabel;
    @FXML private Label adresseLabel;
    @FXML private Label statutLabel;
    @FXML private Label dateCreationLabel;

    @FXML private TableView<HistoriqueSuivi> historiqueTableView;
    @FXML private TableColumn<HistoriqueSuivi, String> dateColumn;
    @FXML private TableColumn<HistoriqueSuivi, String> statutHistoriqueColumn;
    @FXML private TableColumn<HistoriqueSuivi, String> localisationColumn;
    @FXML private TableColumn<HistoriqueSuivi, String> commentaireColumn;

    @FXML private Label localisationActuelleLabel;
    @FXML private Button voirCarteButton;
    @FXML private Label derniereMajLabel;
    @FXML private Button actualiserButton;

    @FXML private CheckBox notificationEmailCheckBox;
    @FXML private CheckBox notificationSmsCheckBox;
    @FXML private Button sauvegarderNotificationsButton;

    private Colis currentColis;

    @FXML
    public void initialize() {
        resultatContainer.setVisible(false);

        // Setup table columns
        dateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
        );

        statutHistoriqueColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatut())
        );

        localisationColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLocalisation())
        );

        commentaireColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCommentaire())
        );
    }

    @FXML
    private void handleRechercher() {
        String numeroSuivi = numeroSuiviField.getText().trim();
        if (numeroSuivi.isEmpty()) {
            showAlert("Veuillez entrer un numéro de suivi.");
            return;
        }

        // Ici on suppose que le numéro suivi est l'ID converti en String
        try {
            int id = Integer.parseInt(numeroSuivi);
            currentColis = fetchColisById(id);
        } catch (NumberFormatException e) {
            showAlert("Le numéro de suivi doit être un nombre entier.");
            return;
        }

        if (currentColis == null) {
            showAlert("Colis non trouvé pour le numéro: " + numeroSuivi);
            resultatContainer.setVisible(false);
            return;
        }

        afficherInfosColis(currentColis);
        resultatContainer.setVisible(true);
    }

    private void afficherInfosColis(Colis colis) {
        numeroLabel.setText(String.valueOf(colis.getId()));
        destinataireLabel.setText(colis.getDescription());
        adresseLabel.setText(colis.getAdresseDestination());
        statutLabel.setText(colis.getStatut());
        dateCreationLabel.setText(colis.getDateCreation());

        // Ces infos ne sont pas dans ta classe Colis, tu peux adapter
        localisationActuelleLabel.setText("Non défini");
        derniereMajLabel.setText("Non défini");

        List<HistoriqueSuivi> historique = fetchHistoriqueByColis(colis.getId());
        ObservableList<HistoriqueSuivi> data = FXCollections.observableArrayList(historique);
        historiqueTableView.setItems(data);

        // Notifications par défaut (à adapter si tu ajoutes ces champs)
        notificationEmailCheckBox.setSelected(false);
        notificationSmsCheckBox.setSelected(false);
    }

    @FXML
    private void handleActualiser() {
        if (currentColis == null) {
            showAlert("Aucun colis sélectionné.");
            return;
        }
        currentColis = fetchColisById(currentColis.getId());
        afficherInfosColis(currentColis);
        showAlert("Données mises à jour.");
    }

    @FXML
    private void handleVoirCarte() {
        if (currentColis == null) {
            showAlert("Aucun colis sélectionné.");
            return;
        }
        showAlert("Affichage de la carte non encore implémenté.");
    }

    @FXML
    private void handleSauvegarderNotifications() {
        if (currentColis == null) {
            showAlert("Aucun colis sélectionné.");
            return;
        }

        // TODO: sauvegarder notifications dans la base si tu ajoutes ces champs

        showAlert("Préférences de notification sauvegardées.");
    }

    // TODO: Implement real data fetch methods
    private Colis fetchColisById(int id) {
        // Simule la récupération d'un colis depuis la base
        return null;
    }

    private List<HistoriqueSuivi> fetchHistoriqueByColis(int idColis) {
        // Simule la récupération de l'historique
        return List.of();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
