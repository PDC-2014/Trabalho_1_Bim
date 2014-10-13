package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

import static dao.Conexao.*;

public class ProdutoBD {

	public static Produto getProdutoByNome(String nome, Boolean fechar) {

		Produto produto = null;
		String sql = null;

		try {
			sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto where nome = ?";

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setString(0, nome);

			try (ResultSet rset = stmt.executeQuery()) {
				if (rset.next()) {
					produto = new Produto();
					produto.setId(rset.getInt("id"));
					produto.setNome(rset.getString("nome"));
					produto.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
					produto.setPeso(rset.getDouble("peso"));
					produto.setPrecoUnitario(rset.getDouble("precoUnitario"));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produto;
	}

	public static Produto getProdutoByID(Integer id, Boolean fechar) {

		Produto produto = null;
		String sql = null;

		try {
			sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto where id = ?";

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setInt(0, id);
			try (ResultSet rset = stmt.executeQuery()) {
				if (rset.next()) {
					produto = new Produto();
					produto.setId(rset.getInt("id"));
					produto.setNome(rset.getString("nome"));
					produto.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
					produto.setPeso(rset.getDouble("peso"));
					produto.setPrecoUnitario(rset.getDouble("precoUnitario"));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produto;
	}

	public static List<Produto> listarProdutos(Boolean fechar) {

		List<Produto> produtos = new ArrayList<Produto>();
		Produto produto = null;
		String sql = null;

		try {
			sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto";

			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					produto = new Produto();
					produto.setId(rset.getInt("id"));
					produto.setNome(rset.getString("nome"));
					produto.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
					produto.setPeso(rset.getDouble("peso"));
					produto.setPrecoUnitario(rset.getDouble("precoUnitario"));
					produtos.add(produto);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produtos;
	}

	public static void novoProduto(Produto produto, Boolean fechar) {

		String sql = null;

		try {
			sql = "insert into produto (nome, quantidadeDisponivel, peso, precoUnitario, filial) values (?, ?, ?, ?, ?)";

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(0, produto.getNome());
			stmt.setInt(1, produto.getQuantidadeDisp());
			stmt.setDouble(2, produto.getPeso());
			stmt.setDouble(3, produto.getPrecoUnitario());
			stmt.setString(4, produto.getFilial().getCodigo());

			stmt.executeQuery();

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}
	}

}
