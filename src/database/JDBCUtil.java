package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    Connection connection = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String SERVIDOR = "127.0.0.1";
    private final String PORTA = "3306";
    private final String BANCO = "CampoMinado";
    private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO + "?useSSL=false";
    private final String USUARIO = "root";
    private final String SENHA = "root";

    public Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
