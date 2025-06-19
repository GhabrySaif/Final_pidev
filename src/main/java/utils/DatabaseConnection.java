package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/pidev"; // Remplacez avec votre URL de base de données
    private static final String USER = "root";  // Remplacez avec votre utilisateur
    private static final String PASSWORD = "";  // Remplacez avec votre mot de passe

    private static Connection connection = null;

    // Méthode pour établir la connexion à la base de données
    public static Connection getConnection() {
        if (connection == null) {
            try {
                System.out.println("Tentative de connexion à la base de données...");
                System.out.println("URL: " + URL);
                System.out.println("User: " + USER);

                // Enregistrement du driver JDBC (pour MySQL)
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Création de la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion à la base de données établie avec succès.");

                // Test de la connexion
                if (connection != null && !connection.isClosed()) {
                    System.out.println("✅ Connexion active et fonctionnelle.");
                }
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Driver JDBC non trouvé : " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la connexion à la base de données:");
                System.err.println("   Code d'erreur: " + e.getErrorCode());
                System.err.println("   Message: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                if (connection.isClosed()) {
                    System.err.println("⚠️ Connexion fermée, tentative de reconnexion...");
                    connection = null;
                    return getConnection(); // Récursion pour reconnecter
                }
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors de la vérification de la connexion: " + e.getMessage());
            }
        }
        return connection;
    }

    // Méthode pour fermer la connexion à la base de données
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
