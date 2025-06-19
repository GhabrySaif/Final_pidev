package Accèe_SQL;

import models.Historique;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDAO {
    private final Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public HistoriqueDAO() {
        connection = DatabaseConnection.getConnection();
    }
    // Ajouter un nouvel enregistrement dans l'historique
    public boolean ajouter(Historique historique) {
        String query = "INSERT INTO historique_livraisons (livraison_id, statut, date_heure) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, historique.getLivraisonId());
            stmt.setString(2, historique.getStatut());
            stmt.setTimestamp(3, Timestamp.valueOf(historique.getDateAction()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtenir l'historique d'une livraison spécifique
    public List<Historique> obtenirParLivraison(int livraisonId) {
        List<Historique> listeHistorique = new ArrayList<>();
        String query = "SELECT * FROM historique_livraisons WHERE livraison_id = ? ORDER BY date_heure DESC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, livraisonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Historique historique = new Historique(
                        rs.getInt("id"),
                        rs.getInt("livraison_id"),
                        rs.getString("statut"),
                        rs.getTimestamp("date_heure").toLocalDateTime()
                );
                listeHistorique.add(historique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeHistorique;
    }

    // Obtenir tout l'historique
    public List<Historique> obtenirTout() {
        List<Historique> listeHistorique = new ArrayList<>();
        String query = "SELECT * FROM historique_livraisons ORDER BY date_heure DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Historique historique = new Historique(
                        rs.getInt("id"),
                        rs.getInt("livraison_id"),
                        rs.getString("statut"),
                        rs.getTimestamp("date_heure").toLocalDateTime()
                );
                listeHistorique.add(historique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeHistorique;
    }
}
