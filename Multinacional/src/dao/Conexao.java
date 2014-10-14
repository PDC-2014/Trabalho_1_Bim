package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Filial;

public abstract class Conexao {

	public static Filial filial;
	private static Connection connection;
	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() {
		try {
			if (connection != null)
				return connection;

			Class.forName(driverName);
			connection = DriverManager.getConnection(url + filial.getNome().toLowerCase(), username, password);
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}

	public static Connection getConnectionServidor() {
		try {
			Class.forName(driverName);

			String urlMultinacional = url + "brasil";
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

	public static void fecharConexao() {
		try {
			if(connection != null)
				connection.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			connection = null;
		}
	}
	
}
