package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Filial;

public class CompraBD {

    public List<Compra> listarComprasFilial(Boolean fechar) {
        List<Compra> comp = null;

        try {
            Connection con = Conexao.getConnection();
            String sql = "Select cliente_id, data from compra";
            PreparedStatement stmt = con.prepareStatement(sql);
            ClienteBD clienteBD = new ClienteBD();
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Compra c = new Compra();
                    c.setId(rset.getInt("id"));
                    c.setCliente(clienteBD.getClienteByID(rset.getInt("cliente_id"), false));
                    c.setData(rset.getDate("data"));
                    ItemCompraBD itemCompraBD = new ItemCompraBD();
                    c.setItens(itemCompraBD.listarItemCompra(c, false));
                    comp.add(c);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar)
                Conexao.FecharConexao();
        }

        return comp;
    }

    public List<Compra> listarComprasCliente(Cliente cliente, Boolean fechar) {
        List<Compra> comp = null;

        try {
            Connection con = Conexao.getConnection();
            String sql = "Select cliente_id, data from compra where cliente_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(0, cliente.getId());
            ClienteBD clienteBD = new ClienteBD();
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Compra c = new Compra();
                    c.setId(rset.getInt("id"));
                    c.setCliente(clienteBD.getClienteByID(rset.getInt("cliente_id"), false));
                    c.setData(rset.getDate("data"));
                    ItemCompraBD itemCompraBD = new ItemCompraBD();
                    c.setItens(itemCompraBD.listarItemCompra(c, false));
                    comp.add(c);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar)
                Conexao.FecharConexao();
        }

        return comp;
    }

    public void novaCompra(Compra compra, Boolean fechar) {
        Filial filial = Conexao.filial;
        Conexao.filial = compra.getCliente().getFilial();

        try {
            Connection con = Conexao.getConnection();
            String sql = "insert into compra (cliente, data) values (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(0, compra.getCliente().getId());
            stmt.setDate(1, new java.sql.Date(compra.getData().getTime()));
            stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) {
                Conexao.filial = filial;
                Conexao.FecharConexao();
            }
        }
    }
}
