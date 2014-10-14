package dao;

import static dao.Conexao.fecharConexao;
import static dao.Conexao.getConnectionServidor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Filial;

public class FilialBD {

	private static String sql;

	public static List<Filial> listarFiliais() {
		return listarFiliais(true);
	}

	public static List<Filial> listarFiliais(Boolean fechar) {

		List<Filial> filiais = new ArrayList<Filial>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = "SELECT cod, nome, porta FROM filial";

			preparedStatement = getConnectionServidor().prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			filiais = construirListaFiliais(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}
		return filiais;
	}

	public static Filial recuperarFilial(String codigo) {
		return recuperarFilial(codigo, true);
	}

	public static Filial recuperarFilial(String codigo, Boolean fechar) {

		Filial filial = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = "SELECT cod, nome, porta FROM filial WHERE cod = ?";

			preparedStatement = getConnectionServidor().prepareStatement(sql);
			preparedStatement.setString(1, codigo);

			resultSet = preparedStatement.executeQuery();
			filial = construirFilial(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return filial;
	}
	
	private static List<Filial> construirListaFiliais(ResultSet resultSet) throws Exception{
		List<Filial> filiais = new ArrayList<Filial>();
		
		while(resultSet.next())
			filiais.add(montaFilial(resultSet));
		
		return filiais;
	}
	
	private static Filial construirFilial(ResultSet resultSet) throws Exception{
		Filial filial = null;
		
		if (resultSet.next())
			filial = montaFilial(resultSet);
		
		return filial;
	}
	
	private static Filial montaFilial(ResultSet resultSet) throws Exception{
		Filial filial = new Filial();
		
		filial.setCodigo(resultSet.getString("cod"));
		filial.setNome(resultSet.getString("nome"));
		filial.setPorta(resultSet.getInt("porta"));
		
		return filial;
	}

}
