package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Filial;

public class Conexao {
    public static Filial filial;
    private static Connection connection;
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url+filial, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
    
    public static Connection getConnectionServidor() throws SQLException {
        try {
            Class.forName(driverName);

            String urlMultinacional = url+"multinacional";
            connection = DriverManager.getConnection(urlMultinacional, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }

    public static boolean FecharConexao() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
