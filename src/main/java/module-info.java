module org.example.final_pidev {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens controllers to javafx.fxml;
    opens controllers.admin to javafx.fxml;
    opens controllers.client to javafx.fxml;
    opens controllers.livreur to javafx.fxml;
    opens org.example.final_pidev to javafx.fxml;
    exports org.example.final_pidev;
}