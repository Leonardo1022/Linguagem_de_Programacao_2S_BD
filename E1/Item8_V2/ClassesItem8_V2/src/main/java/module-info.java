module org.example.classesitem8_v3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires javafx.graphics;


    opens JavaFX to javafx.fxml;
    exports JavaFX;
}