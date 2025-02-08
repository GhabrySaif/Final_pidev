package controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Livraison;
import models.Utilisateur;
//import services.LivraisonService;
import services.UtilisateurService;
//import services.StatistiquesService;

public class AdminDashboardController {

    @FXML
    private ListView<Livraison> livraisonListView;  // Liste des livraisons à afficher pour l'admin
    @FXML
    private ListView<Utilisateur> utilisateurListView;  // Liste des utilisateurs (clients et livreurs)
    @FXML
    private TextArea statsTextArea;  // Zone de texte pour afficher les statistiques
    @FXML
    private Button updateUserButton;  // Bouton pour mettre à jour les informations utilisateur
    @FXML
    private Button refreshStatsButton;  // Bouton pour rafraîchir les statistiques

//    private LivraisonService livraisonService;
    private UtilisateurService utilisateurService;
//    private StatistiquesService statistiquesService;
    private ObservableList<Livraison> livraisonsList;
    private ObservableList<Utilisateur> utilisateursList;

    public AdminDashboardController() {
//        livraisonService = new LivraisonService();
        utilisateurService = new UtilisateurService();
//        statistiquesService = new StatistiquesService();
    }

    // Méthode appelée lors de l'initialisation de la vue
    @FXML
    private void initialize() {
        System.out.println("Initialisation du tableau de bord administrateur...");

//        livraisonsList = FXCollections.observableArrayList(livraisonService.obtenirToutesLesLivraisons());
        utilisateursList = FXCollections.observableArrayList(utilisateurService.obtenirTousLesUtilisateurs());

        livraisonListView.setItems(livraisonsList);
        utilisateurListView.setItems(utilisateursList);

        // Sélectionner une livraison et afficher ses détails
        livraisonListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showLivraisonDetails(newValue);
            }
        });

        // Sélectionner un utilisateur et afficher ses détails
        utilisateurListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showUtilisateurDetails(newValue);
            }
        });
    }



    // Afficher les détails d'une livraison
    private void showLivraisonDetails(Livraison livraison) {
        String details = "ID Livraison: " + livraison.getId() + "\n" +
                "Colis ID: " + livraison.getColisId() + "\n" +
                "Livreur ID: " + livraison.getLivreurId() + "\n" +
                "Adresse de Livraison: " + livraison.getAdresseLivraison() + "\n" +
                "Statut: " + livraison.getStatut();
        statsTextArea.setText(details);
    }

    // Afficher les détails d'un utilisateur
    private void showUtilisateurDetails(Utilisateur utilisateur) {
        String details = "ID Utilisateur: " + utilisateur.getId() + "\n" +
                "Nom: " + utilisateur.getUsername() + "\n" +
                "Email: " + utilisateur.getEmail() + "\n" +
                "Type: " + utilisateur.getRole();  // Type peut être "Client" ou "Livreur"
        statsTextArea.setText(details);
    }

    // Méthode pour rafraîchir les statistiques
    @FXML
    private void handleRefreshStats() {
        System.out.println("Rafraîchissement des statistiques...");
//        String stats = statistiquesService.obtenirStatistiquesLivraisons();
//        statsTextArea.setText(stats);
    }

    // Méthode pour mettre à jour les informations d'un utilisateur
    @FXML
    private void handleUpdateUser() {
        Utilisateur selectedUser = utilisateurListView.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            showAlert(AlertType.WARNING, "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur pour mettre à jour ses informations.");
            return;
        }

        // Exemple de mise à jour de l'utilisateur
        boolean updated = utilisateurService.mettreAJourUtilisateur(
                selectedUser.getId(),
                selectedUser.getUsername(),
                selectedUser.getEmail(),
                selectedUser.getMotDePasse(),
                selectedUser.getRole()
        );

        if (updated) {
            showAlert(AlertType.INFORMATION, "Utilisateur mis à jour", "Les informations de l'utilisateur ont été mises à jour avec succès.");
            refreshUtilisateurList();  // Rafraîchir la liste des utilisateurs
        } else {
            showAlert(AlertType.ERROR, "Erreur de mise à jour", "Il y a eu un problème lors de la mise à jour des informations de l'utilisateur.");
        }
    }

    // Rafraîchir la liste des livraisons
    private void refreshLivraisonsList() {
        System.out.println("Rafraîchissement de la liste des livraisons...");
//        livraisonsList.setAll(livraisonService.obtenirToutesLesLivraisons());
    }

    // Rafraîchir la liste des utilisateurs
    private void refreshUtilisateurList() {
        System.out.println("Rafraîchissement de la liste des utilisateurs...");
        utilisateursList.setAll(utilisateurService.obtenirTousLesUtilisateurs());
    }

    // Méthode pour afficher une alerte
//    private void showAlert(AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}