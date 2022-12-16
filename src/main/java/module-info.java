module java{
    requires javafx.controls;
    requires javafx.fxml;


    opens controller to javafx.fxml;
    exports controller;
    opens view to javafx.fxml;
    exports  view;
}