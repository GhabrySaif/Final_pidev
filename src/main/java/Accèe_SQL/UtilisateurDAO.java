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
        if (connection == null) {
            System.err.println("Erreur: Connexion à la base de données non établie");
            return false;
        }

        String query = "INSERT INTO utilisateurs (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("Tentative d'ajout utilisateur: " + utilisateur.getUsername() + ", " + utilisateur.getEmail() + ", " + utilisateur.getRole());

            stmt.setString(1, utilisateur.getUsername());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getPassword());
            stmt.setString(4, utilisateur.getRole());

            int result = stmt.executeUpdate();
            System.out.println("Résultat de l'insertion: " + result + " ligne(s) affectée(s)");
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout de l'utilisateur:");
            System.err.println("Code d'erreur: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour un utilisateur
    public boolean mettreAJour(Utilisateur utilisateur) {
        String query = "UPDATE utilisateurs SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, utilisateur.getUsername());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getPassword());
            stmt.setString(4, utilisateur.getRole());
            stmt.setInt(5, utilisateur.getId());
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

    // Vérifier si un nom d'utilisateur existe déjà
    public boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM utilisateurs WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification du nom d'utilisateur: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
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

    // Récupérer les utilisateurs par rôle
    public List<Utilisateur> obtenirParRole(String role) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateurs WHERE role = ? ORDER BY username";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, role);
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
            System.err.println("Erreur lors de la récupération des utilisateurs par rôle: " + e.getMessage());
            e.printStackTrace();
        }
        return utilisateurs;
    }
}
