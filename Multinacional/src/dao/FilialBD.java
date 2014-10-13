package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Filial;

import static dao.Conexao.*;

public class FilialBD {

	public static List<Filial> listarFiliais() {
		return listarFiliais(true);
	}

	public static List<Filial> listarFiliais(Boolean fechar) {

		List<Filial> filiais = new ArrayList<Filial>();
		Filial filial = null;
		String sql = null;

		try {

			sql = "SELECT cod, nome, porta FROM filial";

			PreparedStatement stmt = getConnectionServidor().prepareStatement(sql);

			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				filial = new Filial();
				filial.setCodigo(rset.getString("cod"));
				filial.setNome(rset.getString("nome"));
				filial.setPorta(rset.getInt("porta"));
				filiais.add(filial);
			}

		} catch (SQLException exception) {
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
		String sql = null;

		try {
			sql = "SELECT cod, nome, porta FROM filial WHERE cod = ?";

			PreparedStatement stmt = getConnectionServidor().prepareStatement(sql);
			stmt.setString(1, codigo);

			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				filial = new Filial();
				filial.setCodigo(rset.getString("cod"));
				filial.setNome(rset.getString("nome"));
				filial.setPorta(rset.getInt("porta"));
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return filial;
	}

}
