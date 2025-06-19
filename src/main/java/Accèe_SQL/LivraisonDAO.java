package Accèe_SQL;

import models.Livraison;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonDAO {
    private final Connection connection;

    // Constructeur qui initialise la connexion
    public LivraisonDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ajouter une nouvelle livraison
    public boolean ajouter(Livraison livraison) {
        String query = "INSERT INTO livraisons (colis_id, livreur_id, date_livraison, statut) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, livraison.getColisId());
            stmt.setInt(2, livraison.getLivreurId());
            if (livraison.getDateLivraison() != null && !livraison.getDateLivraison().isEmpty()) {
                stmt.setTimestamp(3, Timestamp.valueOf(livraison.getDateLivraison()));
            } else {
                stmt.setTimestamp(3, null);
            }
            stmt.setString(4, livraison.getStatut());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mettre à jour une livraison
    public boolean mettreAJour(Livraison livraison) {
        String query = "UPDATE livraisons SET colis_id = ?, livreur_id = ?, date_livraison = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, livraison.getColisId());
            stmt.setInt(2, livraison.getLivreurId());
            if (livraison.getDateLivraison() != null && !livraison.getDateLivraison().isEmpty()) {
                stmt.setTimestamp(3, Timestamp.valueOf(livraison.getDateLivraison()));
            } else {
                stmt.setTimestamp(3, null);
            }
            stmt.setString(4, livraison.getStatut());
            stmt.setInt(5, livraison.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Supprimer une livraison
    public boolean supprimer(int id) {
        String query = "DELETE FROM livraisons WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtenir une livraison par son ID
    public Livraison obtenirParId(int id) {
        String query = "SELECT * FROM livraisons WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setId(rs.getInt("id"));
                livraison.setColisId(rs.getInt("colis_id"));
                livraison.setLivreurId(rs.getInt("livreur_id"));
                Timestamp timestamp = rs.getTimestamp("date_livraison");
                if (timestamp != null) {
                    livraison.setDateLivraison(timestamp.toString());
                }
                livraison.setStatut(rs.getString("statut"));
                return livraison;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtenir toutes les livraisons
    public List<Livraison> obtenirToutes() {
        List<Livraison> listeLivraisons = new ArrayList<>();
        String query = "SELECT * FROM livraisons";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setId(rs.getInt("id"));
                livraison.setColisId(rs.getInt("colis_id"));
                livraison.setLivreurId(rs.getInt("livreur_id"));
                Timestamp timestamp = rs.getTimestamp("date_livraison");
                if (timestamp != null) {
                    livraison.setDateLivraison(timestamp.toString());
                }
                livraison.setStatut(rs.getString("statut"));
                listeLivraisons.add(livraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeLivraisons;
    }

    // Obtenir les livraisons d'un livreur spécifique
    public List<Livraison> obtenirParLivreur(int livreurId) {
        List<Livraison> listeLivraisons = new ArrayList<>();
        String query = "SELECT * FROM livraisons WHERE livreur_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, livreurId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setId(rs.getInt("id"));
                livraison.setColisId(rs.getInt("colis_id"));
                livraison.setLivreurId(rs.getInt("livreur_id"));
                Timestamp timestamp = rs.getTimestamp("date_livraison");
                if (timestamp != null) {
                    livraison.setDateLivraison(timestamp.toString());
                }
                livraison.setStatut(rs.getString("statut"));
                listeLivraisons.add(livraison);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeLivraisons;
    }

    // Changer le statut d'une livraison
    public boolean changerStatut(int livraisonId, String statut) {
        String query = "UPDATE livraisons SET statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, statut);
            stmt.setInt(2, livraisonId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
