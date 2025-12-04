module org.example.classesitem8_v3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.web;
    requires java.net.http;


    opens JavaFX to javafx.fxml;
    exports JavaFX;
}