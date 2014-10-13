package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;

public class ClienteBD {
    public Cliente getClienteByID(Integer id, Boolean fechar) {
        Cliente cli = null;
        
        try {
            Connection con = Conexao.getConnection();
            String sql = "Select id, nome, cpf, dataNasci, filial from cliente where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(0, id);
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
            String sql = "Select id, nome, cpf, dataNasci, filial from cliente where nome = ?";
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
    
    public void novoCliente(Cliente cliente, Boolean fechar) {
        try {
            Connection con = Conexao.getConnection();
            String sql = "insert into cliente (nome, cpf, dataNasci, filial) values (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, cliente.getNome());
            stmt.setString(1, cliente.getCpf());
            stmt.setDate(2, new java.sql.Date(cliente.getDataNascimento().getTime()));
            stmt.setString(3, cliente.getFilial().getCodigo());
            stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Deu ruim...");
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
    }
}
