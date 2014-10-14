package dao;

import static dao.ClienteBD.getClienteByCod;
import static dao.Conexao.fecharConexao;
import static dao.Conexao.getConnection;
import static dao.ItemCompraBD.listarItemCompra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Compra;
import model.Filial;
import model.ItemCompra;

public class CompraBD {

	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static String sql;
	
	private static void initVars(){
		preparedStatement = null;
		resultSet = null;
		sql = null;
	}
	
	public static List<Compra> listarComprasFilial() {
		return listarComprasFilial(true);
	}
	
	public static List<Compra> listarComprasFilial(Boolean fechar) {

		List<Compra> compras = new ArrayList<Compra>();
		initVars();

		try {
			sql = "Select id, data_compra, cliente_cod from compra";

			preparedStatement = getConnection().prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			compras = construirListaCompras(resultSet);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return compras;
	}

	public static List<Compra> listarComprasCliente(Cliente cliente) {
		return listarComprasCliente(cliente, true);
	}
	
	public static List<Compra> listarComprasCliente(Cliente cliente, Boolean fechar) {

		List<Compra> compras = new ArrayList<Compra>();
		initVars();

		try {
			sql = "Select id, data_compra, cliente_cod from compra where cliente_cod = ?";

			preparedStatement = getConnection().prepareStatement(sql);
			preparedStatement.setString(1, cliente.getCodigo());

			resultSet = preparedStatement.executeQuery();
			compras = construirListaCompras(resultSet, cliente);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar)
				fecharConexao();
		}

		return compras;
	}

	public static void novaCompra(Compra compra) throws Exception {
		novaCompra(compra, true);
	}
	
	public static void novaCompra(Compra compra, Boolean fechar) throws Exception {

		Filial filial = Conexao.filial;
		filial = compra.getCliente().getFilial();

		try {
			Connection con = getConnection();
			String sql = "insert into compra (data_compra, cliente) values (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(compra.getData().getTime()));
			stmt.setString(2, compra.getCliente().getCodigo());
			stmt.executeQuery();

			for (ItemCompra ic : compra.getItens()) {
				sql = "insert into item_compra (compra, produto, quantidade) values (?, ?, ?)";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, compra.getId());
				stmt.setInt(2, ic.getProduto().getId());
				stmt.setInt(3, ic.getQuantidade());
				stmt.executeQuery();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (fechar) {
				Conexao.filial = filial;
				fecharConexao();
			}
		}
	}
	
	private static List<Compra> construirListaCompras(ResultSet resultSet) throws Exception {
		Cliente cliente = null;
		return construirListaCompras(resultSet, cliente);
	}
	
	private static List<Compra> construirListaCompras(ResultSet resultSet, Cliente cliente) throws Exception {
		List<Compra> compras = new ArrayList<Compra>();
		
		while (resultSet.next())
			compras.add(montaCompra(resultSet, cliente));
		
		return compras;
	}
	
	private static Compra montaCompra(ResultSet resultSet, Cliente cliente) throws Exception {
		Compra compra = new Compra();
		
		compra.setId(resultSet.getInt("id"));
		compra.setData(resultSet.getDate("data_compra"));
		compra.setItens(listarItemCompra(compra, false));
		
		if(cliente == null)
			compra.setCliente(getClienteByCod(resultSet.getString("cliente_cod"), false));
		else
			compra.setCliente(cliente);
		
		return compra;
	}
	
}