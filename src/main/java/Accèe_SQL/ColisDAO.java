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
        if (connection == null) {
            System.err.println("Erreur: Connexion à la base de données non établie");
            return false;
        }

        String query = "INSERT INTO colis (utilisateur_id, description, poids, volume, adresse_destination, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("Tentative d'ajout colis:");
            System.out.println("  Utilisateur ID: " + colis.getUtilisateurId());
            System.out.println("  Description: " + colis.getDescription());
            System.out.println("  Poids: " + colis.getPoids());
            System.out.println("  Volume: " + colis.getVolume());
            System.out.println("  Adresse: " + colis.getAdresseDestination());
            System.out.println("  Statut: " + colis.getStatut());

            stmt.setInt(1, colis.getUtilisateurId());
            stmt.setString(2, colis.getDescription());
            stmt.setDouble(3, colis.getPoids());
            stmt.setDouble(4, colis.getVolume());
            stmt.setString(5, colis.getAdresseDestination());
            stmt.setString(6, colis.getStatut());

            int result = stmt.executeUpdate();
            System.out.println("Résultat de l'insertion: " + result + " ligne(s) affectée(s)");
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout du colis:");
            System.err.println("Code d'erreur: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour un colis
    public boolean mettreAJour(Colis colis) {
        String query = "UPDATE colis SET utilisateur_id = ?, description = ?, poids = ?, volume = ?, adresse_destination = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, colis.getUtilisateurId());
            stmt.setString(2, colis.getDescription());
            stmt.setDouble(3, colis.getPoids());
            stmt.setDouble(4, colis.getVolume());
            stmt.setString(5, colis.getAdresseDestination());
            stmt.setString(6, colis.getStatut());
            stmt.setInt(7, colis.getId());
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
                Colis colis = new Colis();
                colis.setId(rs.getInt("id"));
                colis.setUtilisateurId(rs.getInt("utilisateur_id"));
                colis.setDescription(rs.getString("description"));
                colis.setPoids(rs.getDouble("poids"));
                colis.setVolume(rs.getDouble("volume"));
                colis.setAdresseDestination(rs.getString("adresse_destination"));
                colis.setStatut(rs.getString("statut"));
                return colis;
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
                Colis colis = new Colis();
                colis.setId(rs.getInt("id"));
                colis.setUtilisateurId(rs.getInt("utilisateur_id"));
                colis.setDescription(rs.getString("description"));
                colis.setPoids(rs.getDouble("poids"));
                colis.setVolume(rs.getDouble("volume"));
                colis.setAdresseDestination(rs.getString("adresse_destination"));
                colis.setStatut(rs.getString("statut"));
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

        if (connection == null) {
            System.err.println("Erreur: Connexion à la base de données non établie");
            return listeColis;
        }

        String query = "SELECT * FROM colis WHERE utilisateur_id = ?";
        System.out.println("ColisDAO.obtenirParUtilisateur() - Exécution de la requête: " + query);
        System.out.println("Paramètre utilisateurId: " + utilisateurId);

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, utilisateurId);
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
                Colis colis = new Colis();
                colis.setId(rs.getInt("id"));
                colis.setUtilisateurId(rs.getInt("utilisateur_id"));
                colis.setDescription(rs.getString("description"));
                colis.setPoids(rs.getDouble("poids"));
                colis.setVolume(rs.getDouble("volume"));
                colis.setAdresseDestination(rs.getString("adresse_destination"));
                colis.setStatut(rs.getString("statut"));
                listeColis.add(colis);

                System.out.println("Colis trouvé: ID=" + colis.getId() + ", Description=" + colis.getDescription());
            }

            System.out.println("ColisDAO.obtenirParUtilisateur() - " + count + " colis trouvés pour l'utilisateur "
                    + utilisateurId);
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération des colis par utilisateur:");
            System.err.println("Code d'erreur: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return listeColis;
    }
}