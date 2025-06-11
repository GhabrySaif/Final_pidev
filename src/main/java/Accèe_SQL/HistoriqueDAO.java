//package Accèe_SQL;
//
//import models.Historique;
//import utils.DatabaseConnection;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
public class HistoriqueDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public HistoriqueDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
//
//    // Ajouter un nouvel enregistrement dans l'historique
//    public boolean ajouter(Historique historique) {
//        String query = "INSERT INTO historique (utilisateur_id, colis_id, action, date_action) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, historique.getUtilisateurId());
//            stmt.setInt(2, historique.getColisId());
//            stmt.setString(3, historique.getAction());
//            stmt.setTimestamp(4, Timestamp.valueOf(historique.getDateAction()));
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // Obtenir l'historique d'un colis spécifique
//    public List<Historique> obtenirParColis(int colisId) {
//        List<Historique> listeHistorique = new ArrayList<>();
//        String query = "SELECT * FROM historique WHERE colis_id = ? ORDER BY date_action DESC";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, colisId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Historique historique = new Historique(
//                        rs.getInt("id"),
//                        rs.getInt("utilisateur_id"),
//                        rs.getInt("colis_id"),
//                        rs.getString("action"),
//                        rs.getTimestamp("date_action").toLocalDateTime()
//                );
//                listeHistorique.add(historique);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return listeHistorique;
//    }
//
//    // Obtenir l'historique des actions d'un utilisateur (livreur ou administrateur)
//    public List<Historique> obtenirParUtilisateur(int utilisateurId) {
//        List<Historique> listeHistorique = new ArrayList<>();
//        String query = "SELECT * FROM historique WHERE utilisateur_id = ? ORDER BY date_action DESC";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, utilisateurId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Historique historique = new Historique(
//                        rs.getInt("id"),
//                        rs.getInt("utilisateur_id"),
//                        rs.getInt("colis_id"),
//                        rs.getString("action"),
//                        rs.getTimestamp("date_action").toLocalDateTime()
//                );
//                listeHistorique.add(historique);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return listeHistorique;
//    }
//}
