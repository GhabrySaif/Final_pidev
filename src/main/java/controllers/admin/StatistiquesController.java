package controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import services.StatistiquesService;

public class StatistiquesController {

    @FXML
    private Label totalColisLabel;
    @FXML
    private Label colisLivresLabel;
    @FXML
    private Label colisEnTransitLabel;
    @FXML
    private Label totalLivreursLabel;
    @FXML
    private Label tauxLivraisonLabel;
    @FXML
    private Label tempsMoyenLabel;
    @FXML
    private Label livreurActifLabel;
    @FXML
    private PieChart statutColisChart;
    @FXML
    private BarChart<String, Number> livraisonsParLivreurChart;
    @FXML
    private CategoryAxis livreurAxis;
    @FXML
    private NumberAxis nombreLivraisonsAxis;

    private StatistiquesService statistiquesService;

    public StatistiquesController() {
        statistiquesService = new StatistiquesService();
    }

    @FXML
    private void initialize() {
        // Charger les statistiques réelles
        loadStatistiques();
    }

    private void initializeDefaultData() {
        // Données par défaut pour éviter les erreurs
        totalColisLabel.setText("0");
        colisLivresLabel.setText("0");
        colisEnTransitLabel.setText("0");
        totalLivreursLabel.setText("0");
        tauxLivraisonLabel.setText("0%");
        tempsMoyenLabel.setText("0 jours");
        livreurActifLabel.setText("Aucun");

        // Graphique en secteurs par défaut
        statutColisChart.getData().addAll(
            new PieChart.Data("En attente", 0),
            new PieChart.Data("En transit", 0),
            new PieChart.Data("Livré", 0)
        );

        // Graphique en barres par défaut
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Livraisons");
        series.getData().add(new XYChart.Data<>("Aucun livreur", 0));
        livraisonsParLivreurChart.getData().add(series);
    }

    // Méthode pour charger les vraies statistiques
    private void loadStatistiques() {
        try {
            // Charger les statistiques de base
            int totalColis = statistiquesService.obtenirNombreTotalColis();
            int colisLivres = statistiquesService.obtenirNombreColisLivres();
            int colisEnTransit = statistiquesService.obtenirNombreColisEnTransit();
            int totalLivreurs = statistiquesService.obtenirNombreLivreurs();

            // Mettre à jour les labels
            totalColisLabel.setText(String.valueOf(totalColis));
            colisLivresLabel.setText(String.valueOf(colisLivres));
            colisEnTransitLabel.setText(String.valueOf(colisEnTransit));
            totalLivreursLabel.setText(String.valueOf(totalLivreurs));

            // Calculer et afficher le taux de livraison
            double tauxLivraison = statistiquesService.obtenirTauxLivraison();
            tauxLivraisonLabel.setText(String.format("%.1f%%", tauxLivraison));

            // Afficher le livreur le plus actif
            String livreurActif = statistiquesService.obtenirLivreurLePlusActif();
            livreurActifLabel.setText(livreurActif);

            // Temps moyen (placeholder)
            tempsMoyenLabel.setText("2-3 jours");

            // Mettre à jour les graphiques
            updateCharts(totalColis, colisLivres, colisEnTransit);

        } catch (Exception e) {
            // En cas d'erreur, afficher les données par défaut
            initializeDefaultData();
            e.printStackTrace();
        }
    }

    private void updateCharts(int totalColis, int colisLivres, int colisEnTransit) {
        // Mettre à jour le graphique en secteurs
        statutColisChart.getData().clear();
        int enAttente = totalColis - colisLivres - colisEnTransit;
        if (enAttente < 0) enAttente = 0;

        statutColisChart.getData().addAll(
            new PieChart.Data("En attente", enAttente),
            new PieChart.Data("En transit", colisEnTransit),
            new PieChart.Data("Livré", colisLivres)
        );

        // Mettre à jour le graphique en barres (données simplifiées)
        livraisonsParLivreurChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Livraisons");

        String livreurActif = statistiquesService.obtenirLivreurLePlusActif();
        if (!livreurActif.equals("Aucun")) {
            series.getData().add(new XYChart.Data<>(livreurActif, colisLivres));
        } else {
            series.getData().add(new XYChart.Data<>("Aucun livreur", 0));
        }

        livraisonsParLivreurChart.getData().add(series);
    }
}
