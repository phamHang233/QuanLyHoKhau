module java{
    requires javafx.controls;
    requires javafx.fxml;
    requires mssql.jdbc;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires java.naming;
    requires com.microsoft.sqlserver.jdbc;
//    requires javafx
//
    opens controllers to javafx.fxml;
    exports controllers;
//    opens application to javafx.fxml;
//    exports application;
    opens view to javafx.fxml;
    opens models to javafx.base;
    exports  view;
}