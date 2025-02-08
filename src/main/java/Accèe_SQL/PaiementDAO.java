package Accèe_SQL;

import models.Paiement;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {
    private Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public PaiementDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ajouter un paiement dans la base de données
    public boolean ajouter(Paiement paiement) {
        String query = "INSERT INTO paiements (colis_id, montant, statut, date_paiement) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, paiement.getColisId());
            stmt.setDouble(2, paiement.getMontant());
            stmt.setString(3, paiement.getStatut());
            stmt.setTimestamp(4, Timestamp.valueOf(paiement.getDatePaiement()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour un paiement
    public boolean mettreAJour(Paiement paiement) {
        String query = "UPDATE paiements SET montant = ?, statut = ?, date_paiement = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, paiement.getMontant());
            stmt.setString(2, paiement.getStatut());
            stmt.setTimestamp(3, Timestamp.valueOf(paiement.getDatePaiement()));
            stmt.setInt(4, paiement.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Supprimer un paiement par ID
    public boolean supprimer(int id) {
        String query = "DELETE FROM paiements WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer un paiement par ID
    public Paiement obtenirParId(int id) {
        String query = "SELECT * FROM paiements WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Paiement(
                        rs.getInt("id"),
                        rs.getInt("colis_id"),
                        rs.getDouble("montant"),
                        rs.getString("mode_paiement"),
                        rs.getString("statut"),
                        rs.getTimestamp("date_paiement").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer la liste de tous les paiements
    public List<Paiement> obtenirTous() {
        List<Paiement> paiements = new ArrayList<>();
        String query = "SELECT * FROM paiements ORDER BY date_paiement DESC";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                paiements.add(new Paiement(
                        rs.getInt("id"),
                        rs.getInt("colis_id"),
                        rs.getDouble("montant"),
                        rs.getString("mode_paiement"),
                        rs.getString("statut"),
                        rs.getTimestamp("date_paiement").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }
}
