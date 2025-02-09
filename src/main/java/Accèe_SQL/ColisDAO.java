package Accèe_SQL;

import models.Colis;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColisDAO {
    private final Connection connection;

    // Constructeur pour établir la connexion
    public ColisDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ajouter un colis
    public boolean ajouter(Colis colis) {
        String query = "INSERT INTO colis (name, description, date_creation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, colis.getUtilisateurId());
            stmt.setString(2, colis.getDescription());
            stmt.setDouble(3, colis.getPoids());
            stmt.setString(4, colis.getAdresseDestination());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour un colis
    public boolean mettreAJour(Colis colis) {
        String query = "UPDATE colis SET utilisateur_id = ?, description = ?, poids = ?, adresse_destination = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, colis.getUtilisateurId());
            stmt.setString(2, colis.getDescription());
            stmt.setDouble(3, colis.getPoids());
            stmt.setString(4, colis.getAdresseDestination());
            stmt.setString(5, colis.getStatut());
            stmt.setInt(6, colis.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Supprimer un colis
    public boolean supprimer(int id) {
        String query = "DELETE FROM colis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtenir un colis par son ID
    public Colis obtenirParId(int id) {
        String query = "SELECT * FROM colis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Colis(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getString("description"),
                        rs.getDouble("poids"),
                        rs.getString("adresse_destination"),
                        rs.getString("statut")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtenir tous les colis
    public List<Colis> obtenirTous() {
        List<Colis> listeColis = new ArrayList<>();
        String query = "SELECT * FROM colis";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Colis colis = new Colis(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getString("description"),
                        rs.getDouble("poids"),
                        rs.getString("adresse_destination"),
                        rs.getString("statut")
                );
                listeColis.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeColis;
    }

    // Obtenir les colis par utilisateur
    public List<Colis> obtenirParUtilisateur(int utilisateurId) {
        List<Colis> listeColis = new ArrayList<>();
        String query = "SELECT * FROM colis WHERE utilisateur_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, utilisateurId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Colis colis = new Colis(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getString("description"),
                        rs.getDouble("poids"),
                        rs.getString("adresse_destination"),
                        rs.getString("statut")
                );
                listeColis.add(colis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeColis;
    }
}