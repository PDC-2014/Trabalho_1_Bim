package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;

public class ClienteBD {

	public Cliente getClienteByID(Integer id, Boolean fechar) {
		
		Cliente cliente = null;

		try {
			String sql = "SELECT cod, nome, cpf, data_nascimento FROM cliente WHERE cod like ?";
			
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(0, "___"+id);
			
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					cliente = new Cliente();
					cliente.setId(Integer.parseInt(rset.getString("cod").substring(3)));
					cliente.setNome(rset.getString("nome"));
					cliente.setCpf(rset.getString("cpf"));
					cliente.setDataNascimento(rset.getDate("data_nascimento"));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				Conexao.FecharConexao();
		}

		return cliente;
	}

	public Cliente getClienteByNome(String nome, Boolean fechar) {
		
		Cliente cliente = null;

		try {
			String sql = "SELECT id, nome, cpf, dataNasci, filial FROM cliente WHERE nome = ?";
			
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(0, nome);
			
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					cliente = new Cliente();
					cliente.setId(rset.getInt("id"));
					cliente.setNome(rset.getString("nome"));
					cliente.setCpf(rset.getString("cpf"));
					cliente.setDataNascimento(rset.getDate("dataNascimento"));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				Conexao.FecharConexao();
		}

		return cliente;
	}

	public void novoCliente(Cliente cliente, Boolean fechar) {
		try {
			String sql = "INSERT INTO cliente (cod, nome, cpf, data_nascimento) VALUES (?, ?, ?, ?)";
			
			Connection connection = Conexao.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(0, cliente.getFilial().getCodigo()+cliente.getId());
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
			
			stmt.executeQuery();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				Conexao.FecharConexao();
		}
	}
}
