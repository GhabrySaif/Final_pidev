package controllers.client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Utilisateur;

public class MonProfilController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;

    @FXML private PasswordField motDePasseActuelField;
    @FXML private PasswordField nouveauMotDePasseField;
    @FXML private PasswordField confirmerMotDePasseField;
    @FXML private Button changerMotDePasseButton;

    @FXML private Button sauvegarderInfosButton;
    @FXML private Button modifierInfosButton;
    @FXML private Button annulerInfosButton;

    private Utilisateur currentUser;

    @FXML
    public void initialize() {
        setEditablePersonalInfo(false);
        sauvegarderInfosButton.setVisible(false);
        annulerInfosButton.setVisible(false);
    }

    public void setCurrentUser(Utilisateur user) {
        this.currentUser = user;
        loadUserData();
    }

    private void loadUserData() {
        if (currentUser == null) return;

        usernameField.setText(currentUser.getUsername());
        emailField.setText(currentUser.getEmail());
    }

    @FXML
    private void handleModifierInfos() {
        setEditablePersonalInfo(true);
        modifierInfosButton.setVisible(false);
        sauvegarderInfosButton.setVisible(true);
        annulerInfosButton.setVisible(true);
    }

    @FXML
    private void handleSauvegarderInfos() {
        currentUser.setUsername(usernameField.getText());
        currentUser.setEmail(emailField.getText());

        // TODO: Persister les modifications dans la base/service

        setEditablePersonalInfo(false);
        modifierInfosButton.setVisible(true);
        sauvegarderInfosButton.setVisible(false);
        annulerInfosButton.setVisible(false);
        showAlert("Informations personnelles sauvegardées.");
    }

    @FXML
    private void handleAnnulerInfos() {
        loadUserData();
        setEditablePersonalInfo(false);
        modifierInfosButton.setVisible(true);
        sauvegarderInfosButton.setVisible(false);
        annulerInfosButton.setVisible(false);
    }

    private void setEditablePersonalInfo(boolean editable) {
        usernameField.setEditable(editable);
        emailField.setEditable(editable);
    }

    @FXML
    private void handleChangerMotDePasse() {
        String currentPass = motDePasseActuelField.getText();
        String newPass = nouveauMotDePasseField.getText();
        String confirmPass = confirmerMotDePasseField.getText();

        // Il faut implémenter la méthode checkPassword dans Utilisateur si tu veux faire cette vérification
        if (!checkPassword(currentPass)) {
            showAlert("Le mot de passe actuel est incorrect.");
            return;
        }

        if (newPass == null || newPass.isEmpty()) {
            showAlert("Le nouveau mot de passe ne peut pas être vide.");
            return;
        }

        if (!newPass.equals(confirmPass)) {
            showAlert("Les mots de passe ne correspondent pas.");
            return;
        }

        currentUser.setPassword(newPass);

        // TODO: Persister le nouveau mot de passe

        showAlert("Mot de passe changé avec succès.");

        motDePasseActuelField.clear();
        nouveauMotDePasseField.clear();
        confirmerMotDePasseField.clear();
    }

    private boolean checkPassword(String inputPassword) {
        // Simple vérification basique, adapte selon ton hash / méthode réelle
        return currentUser.getPassword().equals(inputPassword);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
