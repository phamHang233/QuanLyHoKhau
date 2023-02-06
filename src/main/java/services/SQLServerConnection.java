package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {
    public static Connection getSqlConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://LAPTOP-UATS39C2\\SQLEXPRESS01:1433;encrypt=true;database=Quan_ly_nhan_khau;trustServerCertificate= true;";
        String userName = "sa";
        String password = "HangHeo1208";
        Connection con = DriverManager.getConnection(connectionUrl, userName, password);
        return con;

    }
}

