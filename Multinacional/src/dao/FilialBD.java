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
            Connection con = Conexao.getConnectionServidor();
            String sql = "Select id,nome,codigo from filial";
            PreparedStatement stmt = con.prepareStatement(sql);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    Filial f = new Filial();
                    f.setCodigo(rset.getString("codigo"));
                    f.setNome(rset.getString("nome"));
                    f.setCodigo(rset.getString("codigo"));
                    filiais.add(f);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (fechar) Conexao.FecharConexao();
        }
        return filiais;
    }
}
