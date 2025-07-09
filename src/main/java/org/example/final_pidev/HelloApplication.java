package org.example.final_pidev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load your custom icon
        try {
            // Replace with your actual full path
            String iconPath = "file:///C:/Users/Lenovo/Downloads/R.png";
            Image appIcon = new Image(iconPath);
            stage.getIcons().add(appIcon);
        } catch (Exception e) {
            System.out.println("Could not load custom icon: " + e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle(" EDX ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}