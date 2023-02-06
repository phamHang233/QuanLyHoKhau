package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {
    public static Connection getSqlConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName= Quanlyhokhau; encrypt=true; trustServerCertificate=true";
            String userName = "sa";
            String password = "123456";
            Connection con = DriverManager.getConnection(connectionUrl, userName, password);
            return con;
    }
}

