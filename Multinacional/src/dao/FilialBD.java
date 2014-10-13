package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Filial;

public class FilialBD {
	
    public List<Filial> listarFiliais(Boolean fechar) {
    	
		List<Filial> filiais = new ArrayList<>();

		try {

			String sql = "SELECT cod, nome, porta FROM filial";

			Connection con = Conexao.getConnectionServidor();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			try (ResultSet rset = stmt.executeQuery()) {
				while (rset.next()) {
					Filial f = new Filial();
					f.setCodigo(rset.getString("cod"));
					f.setNome(rset.getString("nome"));
					f.setPorta(rset.getInt("porta"));
					filiais.add(f);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (fechar)
				Conexao.FecharConexao();
		}
		return filiais;
    }

	public Filial recuperarFilial(String codigo, Boolean fechar) {
		
		Filial filial = null;
        
        try {
        	
        	String sql = "SELECT cod, nome, porta FROM filial WHERE cod = ?";
        	
            Connection con = Conexao.getConnectionServidor();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(0, codigo);
            
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Filial f = new Filial();
                    f.setCodigo(rset.getString("cod"));
					f.setNome(rset.getString("nome"));
					f.setPorta(rset.getInt("porta"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        return filial;
	}
}
