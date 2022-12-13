module com.example.beginning {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.beginning to javafx.fxml;
    exports com.example.beginning;
    exports com.example.beginning.controller;
    opens com.example.beginning.controller to javafx.fxml;
}