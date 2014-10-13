package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Compra;
import model.ItemCompra;

public class ItemCompraBD {
    public List<ItemCompra> listarItemCompra(Compra compra, Boolean fechar) {
        List<ItemCompra> comp = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select produto_id, quantidade from item_compra where compra_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(0, compra.getId());
            ProdutoBD produtoBD = new ProdutoBD();
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    ItemCompra ic = new ItemCompra();
                    ic.setProduto(produtoBD.getProdutoByID(rset.getInt("produto_id"), false));
                    ic.setQuantidade(rset.getInt("quantidade"));
                    comp.add(ic);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return comp;
    }
}
