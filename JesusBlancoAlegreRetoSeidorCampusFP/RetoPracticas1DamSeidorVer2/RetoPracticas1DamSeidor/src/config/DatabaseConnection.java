package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/akihabara_db";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void checkDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            if (!conn.getMetaData().getTables(null, null, "productos", null).next()) {
                throw new SQLException("Ejecuta primero crear_tabla.sql");
            }
        }
    }
}