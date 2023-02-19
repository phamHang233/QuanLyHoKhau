package view;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class Main extends Application {
    public static Calendar calendar = Calendar.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/login-view.fxml"));
        Scene scene1 = new Scene(root, 459, 322);
        stage.setTitle("Quản Lý Nhân Khẩu !");
        stage.setScene(scene1);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args)  {
        launch();

    }
}
