package dao;

import static dao.Conexao.fecharConexao;
import static dao.Conexao.getConnection;
import static dao.FilialBD.recuperarFilial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import model.Cliente;

public class ClienteBD {

	private static Cliente cliente;
	private static String sql;
	private static PreparedStatement preparedStatement;

	private static void initVars() {
		cliente = null;
		sql = null;
		preparedStatement = null;
	}

	public static Cliente getClienteByCod(String cod) {
		return getClienteByCod(cod, true);
	}

	public static Cliente getClienteByCod(String cod, Boolean fechar) {

		initVars();

		try {
			sql = "SELECT cod, nome, cpf, data_nascimento FROM cliente WHERE cod = ?";

			preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, cod);

			ResultSet resultSet = preparedStatement.executeQuery();
			cliente = construirCliente(resultSet);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return cliente;
	}

	public static Cliente getClienteByNome(String nome) {
		return getClienteByNome(nome, true);
	}

	public static Cliente getClienteByNome(String nome, Boolean fechar) {

		initVars();

		try {
			sql = "SELECT cod, nome, cpf, data_nascimento FROM cliente WHERE nome = ?";

			preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, nome);

			ResultSet resultSet = preparedStatement.executeQuery();
			cliente = construirCliente(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return cliente;
	}

	public static void novoCliente(Cliente cliente) throws Exception {
		novoCliente(cliente, true);
	}

	public static void novoCliente(Cliente cliente, Boolean fechar) throws Exception {

		initVars();
		String codigoCliente;

		sql = "INSERT INTO cliente (cod, nome, cpf, data_nascimento) VALUES (?, ?, ?, ?)";

		preparedStatement = getConnection().prepareStatement(sql);

		if(cliente.getCodigo() == null || "".equals(cliente.getCodigo()))
			codigoCliente = Conexao.filial.getCodigo() + new Date().getTime(); 
		else
			codigoCliente = cliente.getCodigo();
		
		preparedStatement.setString(1, codigoCliente);
		preparedStatement.setString(2, cliente.getNome());
		preparedStatement.setString(3, cliente.getCpf());
		preparedStatement.setDate(4, new java.sql.Date(cliente.getDataNascimento().getTime()));

		preparedStatement.executeUpdate();

		if (fechar)
			fecharConexao();
	}

	private static Cliente construirCliente(ResultSet resultSet) throws Exception {

		Cliente cliente = null;

		if (resultSet.next())
			cliente = montaCliente(resultSet);

		return cliente;
	}

	private static Cliente montaCliente(ResultSet resultSet) throws Exception {

		Cliente cliente = new Cliente();

		cliente.setCodigo(resultSet.getString("cod"));
		cliente.setFilial(recuperarFilial(resultSet.getString("cod").substring(0, 2)));
		cliente.setNome(resultSet.getString("nome"));
		cliente.setCpf(resultSet.getString("cpf"));
		cliente.setDataNascimento(resultSet.getDate("data_nascimento"));

		return cliente;
	}
}