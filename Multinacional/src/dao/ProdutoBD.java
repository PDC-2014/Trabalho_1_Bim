package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Produto;

public class ProdutoBD {
    public Produto getProdutoByNome(String nome, Boolean fechar) {
        Produto prod = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, nome);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    prod = new Produto();
                    prod.setId(rset.getInt("id"));
                    prod.setNome(rset.getString("nome"));
                    prod.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
                    prod.setPeso(rset.getDouble("peso"));
                    prod.setPrecoUnitario(rset.getDouble("precoUnitario"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return prod;
    }
    
    public Produto getProdutoByID(Integer id, Boolean fechar) {
        Produto prod = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(0, id);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    prod = new Produto();
                    prod.setId(rset.getInt("id"));
                    prod.setNome(rset.getString("nome"));
                    prod.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
                    prod.setPeso(rset.getDouble("peso"));
                    prod.setPrecoUnitario(rset.getDouble("precoUnitario"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return prod;
    }
    
    public List<Produto> listarProdutos(Boolean fechar) {
        List<Produto> prod = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, nome, quantidadeDisp, peso, precoUnitario from produto";
            PreparedStatement stmt = con.prepareStatement(sql);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Produto p = new Produto();
                    p.setId(rset.getInt("id"));
                    p.setNome(rset.getString("nome"));
                    p.setQuantidadeDisp(rset.getInt("quantidadeDisp"));
                    p.setPeso(rset.getDouble("peso"));
                    p.setPrecoUnitario(rset.getDouble("precoUnitario"));
                    prod.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return prod;
    }
    
    public void novoProduto(Produto produto, Boolean fechar) {
        try {
            Connection con = Conexao.getConnection();
            String sql = "insert into produto (nome, quantidadeDisponivel, peso, precoUnitario, filial) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, produto.getNome());
            stmt.setInt(1, produto.getQuantidadeDisp());
            stmt.setDouble(2, produto.getPeso());
            stmt.setDouble(3, produto.getPrecoUnitario());
            stmt.setString(4, produto.getFilial().getCodigo());
            stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
    }
}
