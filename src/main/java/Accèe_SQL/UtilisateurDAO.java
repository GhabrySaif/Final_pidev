package Accèe_SQL;

import models.Utilisateur;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class UtilisateurDAO {
    private final Connection connection;

    // Constructeur qui initialise la connexion à la base de données
    public UtilisateurDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ajouter un utilisateur dans la base de données
    public boolean ajouter(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateurs (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utilisateur.getUsername());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getPassword());
            stmt.setString(5, utilisateur.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour un utilisateur
    public boolean mettreAJour(Utilisateur utilisateur) {
        String query = "UPDATE utilisateurs SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utilisateur.getUsername());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getPassword());
            stmt.setString(5, utilisateur.getRole());
            stmt.setInt(6, utilisateur.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Supprimer un utilisateur par ID
    public boolean supprimer(int id) {
        String query = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Récupérer un utilisateur par ID
    public Utilisateur obtenirParId(int id) {
        String query = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer un utilisateur par email et mot de passe (authentification)
    public Utilisateur authentifier(String username, String password) {
        String query = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> obtenirTous() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs ORDER BY username ";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
}
