package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Compra;
import model.ItemCompra;

import static dao.Conexao.*;

public class ItemCompraBD {

	public static List<ItemCompra> listarItemCompra(Compra compra, Boolean fechar) {

		List<ItemCompra> comp = new ArrayList<ItemCompra>();
		String sql = null;

		try {
			sql = "Select produto_id, quantidade from itemcompra where compra_id = ?";

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, compra.getId());

			ResultSet rset = stmt.executeQuery();
				while (rset.next()) {
					ItemCompra ic = new ItemCompra();
					ic.setProduto(ProdutoBD.getProdutoByID(rset.getInt("produto_id"), false));
					ic.setQuantidade(rset.getInt("quantidade"));
					comp.add(ic);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return comp;
	}
}
