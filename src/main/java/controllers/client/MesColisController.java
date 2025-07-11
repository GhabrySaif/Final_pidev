package controllers.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import models.Colis;

import java.util.List;
import java.util.stream.Collectors;

public class MesColisController {

    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button refreshButton;

    @FXML private TableView<Colis> colisTableView;
    @FXML private TableColumn<Colis, String> idColumn;
    @FXML private TableColumn<Colis, String> descriptionColumn;
    @FXML private TableColumn<Colis, String> adresseDestinationColumn;
    @FXML private TableColumn<Colis, String> statutColumn;
    @FXML private TableColumn<Colis, String> dateCreationColumn;

    @FXML private Button voirDetailsButton;
    @FXML private Button modifierButton;
    @FXML private Button supprimerButton;

    private ObservableList<Colis> colisList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getId()))
        );
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        adresseDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("adresseDestination"));
        statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        loadColis();
        colisTableView.setItems(colisList);
    }

    private void loadColis() {
        colisList.clear();
        // Exemple corrigé (6 arguments selon constructeur existant)
        Colis c1 = new Colis(1, 1, "Colis important", 3.2, "10 rue Paris", "En cours");
        c1.setDateCreation("2025-06-01");  // setter pour dateCreation

        Colis c2 = new Colis(2, 1, "Colis fragile", 1.5, "20 avenue Lyon", "Livré");
        c2.setDateCreation("2025-05-15");  // setter pour dateCreation

        colisList.addAll(c1, c2);
    }


    @FXML
    private void handleSearch(ActionEvent event) {
        String searchTerm = searchField.getText().toLowerCase().trim();
        if (searchTerm.isEmpty()) {
            colisTableView.setItems(colisList);
        } else {
            List<Colis> filtered = colisList.stream()
                    .filter(c -> String.valueOf(c.getId()).contains(searchTerm)
                            || c.getDescription().toLowerCase().contains(searchTerm)
                            || c.getAdresseDestination().toLowerCase().contains(searchTerm)
                            || c.getStatut().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
            colisTableView.setItems(FXCollections.observableArrayList(filtered));
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadColis();
        colisTableView.setItems(colisList);
        searchField.clear();
    }

    @FXML
    private void handleVoirDetails(ActionEvent event) {
        Colis selected = colisTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Voir détails: " + selected);
            // TODO: afficher détails dans une nouvelle fenêtre ou dialog
        } else {
            showAlert("Veuillez sélectionner un colis pour voir les détails.");
        }
    }

    @FXML
    private void handleModifier(ActionEvent event) {
        Colis selected = colisTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Modifier: " + selected);
            // TODO: ouvrir formulaire d'édition
        } else {
            showAlert("Veuillez sélectionner un colis à modifier.");
        }
    }

    @FXML
    private void handleSupprimer(ActionEvent event) {
        Colis selected = colisTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment supprimer le colis " + selected.getId() + " ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    colisList.remove(selected);
                    colisTableView.setItems(colisList);
                    // TODO: supprimer dans la base de données
                }
            });
        } else {
            showAlert("Veuillez sélectionner un colis à supprimer.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
