package controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Utilisateur;
import services.UtilisateurService;

public class GestionClientsController {

    @FXML
    private ListView<Utilisateur> utilisateursListView;  // Liste des utilisateurs
    @FXML
    private TextArea utilisateurDetailsTextArea;  // Zone de texte pour afficher les détails d'un utilisateur
    @FXML
    private TextField nomTextField;  // Champ pour saisir le nom de l'utilisateur
    @FXML
    private TextField emailTextField;  // Champ pour saisir l'email de l'utilisateur
    @FXML
    private TextField roleTextField;  // Champ pour saisir le rôle de l'utilisateur (ex : admin, client, livreur)
    @FXML
    private Button updateUtilisateurButton;  // Bouton pour mettre à jour l'utilisateur
    @FXML
    private Button deleteUtilisateurButton;  // Bouton pour supprimer un utilisateur
    @FXML
    private Button addUtilisateurButton;  // Bouton pour ajouter un nouvel utilisateur

    private final UtilisateurService utilisateurService;
    private ObservableList<Utilisateur> utilisateursList;

    public GestionClientsController() {
        utilisateurService = new UtilisateurService();
    }

    // Méthode appelée lors de l'initialisation de la vue
    @FXML
    private void initialize() {
        // Charger uniquement les clients
        utilisateursList = FXCollections.observableArrayList(utilisateurService.obtenirClients());
        utilisateursListView.setItems(utilisateursList);

        // Sélectionner un utilisateur et afficher ses détails
        utilisateursListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showUtilisateurDetails(newValue);
            }
        });
    }

    // Afficher les détails d'un utilisateur
    private void showUtilisateurDetails(Utilisateur utilisateur) {
        String details = "ID: " + utilisateur.getId() + "\n" +
                "Nom: " + utilisateur.getUsername() + "\n" +
                "Email: " + utilisateur.getEmail() + "\n" +
                "Rôle: " + utilisateur.getRole();
        utilisateurDetailsTextArea.setText(details);
    }

    // Mettre à jour les informations d'un utilisateur
    @FXML
    private void handleUpdateUtilisateur() {
        Utilisateur selectedUtilisateur = utilisateursListView.getSelectionModel().getSelectedItem();

        if (selectedUtilisateur == null) {
            showAlert(AlertType.WARNING, "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à mettre à jour.");
            return;
        }

        String nom = nomTextField.getText();
        String email = emailTextField.getText();
        String role = roleTextField.getText();

        if (nom.isEmpty() || email.isEmpty() || role.isEmpty()) {
            showAlert(AlertType.WARNING, "Informations manquantes", "Tous les champs doivent être remplis.");
            return;
        }

        // Mettre à jour les informations de l'utilisateur
        selectedUtilisateur.setUsername(nom);
        selectedUtilisateur.setEmail(email);
        selectedUtilisateur.setRole(role);

        boolean updated = utilisateurService.mettreAJourUtilisateur(selectedUtilisateur.getId(),selectedUtilisateur.getUsername(),selectedUtilisateur.getEmail(),selectedUtilisateur.getPassword(),selectedUtilisateur.getRole());

        if (updated) {
            showAlert(AlertType.INFORMATION, "Utilisateur mis à jour", "Les informations de l'utilisateur ont été mises à jour.");
            refreshUtilisateursList();
        } else {
            showAlert(AlertType.ERROR, "Erreur", "Impossible de mettre à jour les informations de l'utilisateur.");
        }
    }

    // Supprimer un utilisateur
    @FXML
    private void handleDeleteUtilisateur() {
        Utilisateur selectedUtilisateur = utilisateursListView.getSelectionModel().getSelectedItem();

        if (selectedUtilisateur == null) {
            showAlert(AlertType.WARNING, "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer.");
            return;
        }

        boolean deleted = utilisateurService.supprimerUtilisateur(selectedUtilisateur.getId());

        if (deleted) {
            showAlert(AlertType.INFORMATION, "Utilisateur supprimé", "L'utilisateur a été supprimé avec succès.");
            refreshUtilisateursList();
        } else {
            showAlert(AlertType.ERROR, "Erreur", "Impossible de supprimer l'utilisateur.");
        }
    }

    // Ajouter un nouvel utilisateur
    @FXML
    private void handleAddUtilisateur() {
        String username = nomTextField.getText();
        String email = emailTextField.getText();
        String role = roleTextField.getText();

        if (username.isEmpty() || email.isEmpty() || role.isEmpty()) {
            showAlert(AlertType.WARNING, "Informations manquantes", "Tous les champs doivent être remplis.");
            return;
        }

        // Créer un mot de passe par défaut
        String defaultPassword = username + "123";

        System.out.println("Tentative d'ajout d'utilisateur depuis le contrôleur:");
        System.out.println("  Username: " + username);
        System.out.println("  Email: " + email);
        System.out.println("  Role: " + role);

        boolean added = utilisateurService.ajouterUtilisateur(username, email, defaultPassword, role);

        if (added) {
            showAlert(AlertType.INFORMATION, "Client ajouté",
                     "Le client a été ajouté avec succès.\nMot de passe par défaut: " + defaultPassword);
            refreshUtilisateursList();
            clearFields();
        } else {
            showAlert(AlertType.ERROR, "Erreur d'ajout",
                     "Impossible d'ajouter le client.\n\n" +
                     "Causes possibles:\n" +
                     "• Nom d'utilisateur déjà existant\n" +
                     "• Problème de connexion à la base de données\n" +
                     "• Email déjà utilisé\n\n" +
                     "Vérifiez la console pour plus de détails.");
        }
    }

    // Vider les champs de saisie
    private void clearFields() {
        nomTextField.clear();
        emailTextField.clear();
        roleTextField.clear();
        utilisateurDetailsTextArea.clear();
    }

    // Rafraîchir la liste des clients
    private void refreshUtilisateursList() {
        utilisateursList.setAll(utilisateurService.obtenirClients());
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