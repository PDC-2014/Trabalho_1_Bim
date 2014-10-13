package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.Filial;

public class ClienteBD {
    public Cliente getClienteByCodigo(String codigo, Boolean fechar) {
        Cliente cli = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, nome, cpf, dataNasci, filial from cliente where codigo = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, codigo);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cli = new Cliente();
                    cli.setId(rset.getInt("id"));
                    cli.setNome(rset.getString("nome"));
                    cli.setCpf(rset.getString("cpf"));
                    cli.setDataNascimento(rset.getDate("dataNasci"));
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return cli;
    }
    
    public Cliente getClienteByNome(String nome, Boolean fechar) {
        Cliente cli = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, codigo, nome, cpf, dataNascimento from cliente where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, nome);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    cli = new Cliente();
                    cli.setId(rset.getInt("id"));
                    cli.setNome(rset.getString("nome"));
                    cli.setCpf(rset.getString("cpf"));
                    cli.setDataNascimento(rset.getDate("dataNascimento"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        
        return cli;
    }
}
