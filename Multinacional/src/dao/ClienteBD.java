package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;

import static dao.Conexao.*;

public class ClienteBD {

	public static Cliente getClienteByID(Integer id) {
		return getClienteByID(id, true);
	}
	
	public static Cliente getClienteByID(Integer id, Boolean fechar) {

		Cliente cliente = null;

		try {
			String sql = "SELECT cod, nome, cpf, data_nascimento FROM cliente WHERE cod like ?";

			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, "___" + id);

			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				cliente = new Cliente();
				cliente.setId(Integer.parseInt(rset.getString("cod").substring(3)));
				cliente.setFilial(FilialBD.recuperarFilial(rset.getString("cod").substring(0, 2)));
				cliente.setNome(rset.getString("nome"));
				cliente.setCpf(rset.getString("cpf"));
				cliente.setDataNascimento(rset.getDate("data_nascimento"));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return cliente;
	}

	public Cliente getClienteByNome(String nome) {
		return getClienteByNome(nome, true);
	}
	
	public Cliente getClienteByNome(String nome, Boolean fechar) {

		Cliente cliente = null;
		String sql = null;

		try {
			sql = "SELECT id, nome, cpf, dataNasci, filial FROM cliente WHERE nome = ?";

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setString(0, nome);

			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				cliente = new Cliente();
				cliente.setId(rset.getInt("id"));
				cliente.setNome(rset.getString("nome"));
				cliente.setCpf(rset.getString("cpf"));
				cliente.setDataNascimento(rset.getDate("dataNascimento"));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return cliente;
	}

	public void novoCliente(Cliente cliente) {
		novoCliente(cliente, true);
	}
	
	public void novoCliente(Cliente cliente, Boolean fechar) {

		String sql = null;

		try {
			sql = "INSERT INTO cliente (cod, nome, cpf, data_nascimento) VALUES (?, ?, ?, ?)";

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(0, cliente.getFilial().getCodigo() + cliente.getId());
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));

			stmt.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}
	}
}