package controllers.client;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AjouterColisController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField telephoneField;
    @FXML private TextField emailField;

    @FXML private TextField adresseField;
    @FXML private TextField villeField;
    @FXML private TextField codePostalField;
    @FXML private TextField paysField;

    @FXML private TextField poidsField;
    @FXML private TextField longueurField;
    @FXML private TextField largeurField;
    @FXML private TextField hauteurField;

    @FXML private ComboBox<String> typeColisComboBox;
    @FXML private ComboBox<String> prioriteComboBox;

    @FXML private TextArea descriptionArea;

    @FXML private Button ajouterButton;
    @FXML private Button annulerButton;

    @FXML
    public void initialize() {
        // Initialize ComboBoxes with example values
        typeColisComboBox.setItems(FXCollections.observableArrayList(
                "Documents", "Vêtements", "Electronique", "Autres"));
        prioriteComboBox.setItems(FXCollections.observableArrayList(
                "Normale", "Urgente", "Très urgente"));

        // Optionally, select defaults
        typeColisComboBox.getSelectionModel().selectFirst();
        prioriteComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleAjouter() {
        // Basic validation example
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty()
                || adresseField.getText().isEmpty() || poidsField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs obligatoires.");
            return;
        }

        try {
            double poids = Double.parseDouble(poidsField.getText());
            if (poids <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le poids doit être un nombre positif.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le poids doit être un nombre valide.");
            return;
        }

        // Here, add logic to save the colis data to database or service
        // For now, just show confirmation
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Le colis a été ajouté avec succès.");

        clearForm();
    }

    @FXML
    private void handleAnnuler() {
        clearForm();
    }

    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        telephoneField.clear();
        emailField.clear();

        adresseField.clear();
        villeField.clear();
        codePostalField.clear();
        paysField.clear();

        poidsField.clear();
        longueurField.clear();
        largeurField.clear();
        hauteurField.clear();

        descriptionArea.clear();

        typeColisComboBox.getSelectionModel().selectFirst();
        prioriteComboBox.getSelectionModel().selectFirst();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
