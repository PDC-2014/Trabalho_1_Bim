package dao;

import static dao.Conexao.fecharConexao;
import static dao.Conexao.getConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoBD {

	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static Produto produto;
	private static String sql;
	
	private static void initVars(){
		preparedStatement = null;
		resultSet = null;
		produto = null;
		sql = null;
	}
	
	public static Produto getProdutoByNome(String nome) {
		return getProdutoByNome(nome, true);
	}

	public static Produto getProdutoByNome(String nome, Boolean fechar) {

		initVars();

		try {
			sql = "Select id, nome, quantidade_disponivel, peso, preco_unitario from produto where nome = ?";

			preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, nome);

			resultSet = preparedStatement.executeQuery();
			produto = construirProduto(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produto;
	}

	public static Produto getProdutoByID(Integer id) {
		return getProdutoByID(id, true);
	}

	public static Produto getProdutoByID(Integer id, Boolean fechar) {

		initVars();

		try {
			sql = "Select id, nome, quantidade_disponivel, peso, preco_unitario from produto where id = ?";

			preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			produto = construirProduto(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produto;
	}

	public static List<Produto> listarProdutos() {
		return listarProdutos(true);
	}

	public static List<Produto> listarProdutos(Boolean fechar) {

		List<Produto> produtos = new ArrayList<Produto>();
		initVars();

		try {
			sql = "Select id, nome, quantidade_disponivel, peso, preco_unitario from produto";

			preparedStatement = getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			produtos = construirListaProduto(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return produtos;
	}

	private static List<Produto> construirListaProduto(ResultSet resultSet) throws Exception {
		List<Produto> produtos = new ArrayList<Produto>();

		while (resultSet.next())
			produtos.add(montaProduto(resultSet));

		return produtos;
	}

	private static Produto construirProduto(ResultSet resultSet) throws Exception {
		Produto produto = null;

		if (resultSet.next())
			produto = montaProduto(resultSet);

		return produto;
	}

	private static Produto montaProduto(ResultSet resultSet) throws Exception {
		Produto produto = new Produto();

		produto.setId(resultSet.getInt("id"));
		produto.setNome(resultSet.getString("nome"));
		produto.setQuantidadeDisp(resultSet.getInt("quantidade_disponivel"));
		produto.setPeso(resultSet.getDouble("peso"));
		produto.setPrecoUnitario(resultSet.getDouble("preco_unitario"));

		return produto;
	}

}
