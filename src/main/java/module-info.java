module java{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires java.naming;
    requires com.microsoft.sqlserver.jdbc;
    requires java.desktop;
//    requires javafx
//
    opens controllers to javafx.fxml;
    exports controllers;


    opens controllers.NVH to javafx.fxml;
    exports controllers.NVH;
    opens controllers.hoKhauControllers to javafx.fxml;
    exports controllers.hoKhauControllers;
    opens controllers.nhankhauControllers to javafx.fxml;
    exports  controllers.nhankhauControllers;
//    opens application to javafx.fxml;
//    exports application;

    opens view to javafx.fxml;
    exports view;
    opens models to javafx.base;

    exports  models;


    exports controllers.ThongKe;
    opens controllers.ThongKe to javafx.fxml;

}