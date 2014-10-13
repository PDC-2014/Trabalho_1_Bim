package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Compra;
import model.Filial;
import model.ItemCompra;

import static dao.Conexao.*;

public class CompraBD {

	public List<Compra> listarComprasFilial(Boolean fechar) {

		List<Compra> compras = new ArrayList<Compra>();
		Compra compra = null;
		String sql = null;

		try {
			sql = "Select cliente_id, data from compra";

			PreparedStatement stmt = getConnection().prepareStatement(sql);

			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				compra = new Compra();
				compra.setId(rset.getInt("id"));
				compra.setCliente(ClienteBD.getClienteByID(rset.getInt("cliente_id"), false));
				compra.setData(rset.getDate("data"));
				ItemCompraBD itemCompraBD = new ItemCompraBD();
				compra.setItens(itemCompraBD.listarItemCompra(compra, false));
				compras.add(compra);
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return compras;
	}

	public List<Compra> listarComprasCliente(Cliente cliente, Boolean fechar) {

		List<Compra> compras = new ArrayList<Compra>();
		Compra compra = null;
		String sql = null;

		try {
			sql = "Select cliente_id, data from compra where cliente_id = ?";

			PreparedStatement stmt = getConnection().prepareStatement(sql);
			stmt.setInt(0, cliente.getId());

			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					compra = new Compra();
					compra.setId(rset.getInt("id"));
					compra.setCliente(ClienteBD.getClienteByID(rset.getInt("cliente_id"), false));
					compra.setData(rset.getDate("data"));
					ItemCompraBD itemCompraBD = new ItemCompraBD();
					compra.setItens(itemCompraBD.listarItemCompra(compra, false));
					compras.add(compra);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return compras;
	}

	public void novaCompra(Compra compra, Boolean fechar) {

		Filial filial = Conexao.filial;
		filial = compra.getCliente().getFilial();

		try {
			Connection con = getConnection();
			String sql = "insert into compra (cliente, data) values (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getCliente().getId());
			stmt.setDate(2, new java.sql.Date(compra.getData().getTime()));
			stmt.executeQuery();

			for (ItemCompra ic : compra.getItens()) {
				sql = "insert into item_compra (compra, produto, quantidade) values (?, ?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, compra.getId());
				stmt.setInt(2, ic.getProduto().getId());
				stmt.setInt(3, ic.getQuantidade());
				stmt.executeQuery();
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if (fechar) {
				Conexao.filial = filial;
				fecharConexao();
			}
		}
	}

}
