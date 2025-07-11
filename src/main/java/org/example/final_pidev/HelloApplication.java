package org.example.final_pidev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    public void start(Stage stage) {
        try {
            // Load custom app icon
            String iconPath = "file:///C:/Users/Lenovo/Downloads/R.png";
            stage.getIcons().add(new Image(iconPath));
        } catch (Exception e) {
            System.out.println("Could not load icon: " + e.getMessage());
        }

        try {
            // Load login FXML
            URL fxmlUrl = getClass().getResource("/login.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML file 'login.fxml' not found in resources.");
            }

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Scene scene = new Scene(loader.load(), 800, 400);
            stage.setScene(scene);
            stage.setTitle("EDX - Connexion");
            stage.show();

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'application:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
