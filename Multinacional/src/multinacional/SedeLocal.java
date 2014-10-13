package multinacional;

import dao.ClienteBD;
import dao.Conexao;
import dao.FilialBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Filial;

public class SedeLocal extends UnicastRemoteObject implements SedeInter {

    public SedeLocal() throws RemoteException {
        super();
    }

    @Override
    public void criarCliente() {

    }

    @Override
    public List<Cliente> buscarClienteNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new ClienteBD().getClienteByNome(nome, true));
        
        List<Filial> filiais = new FilialBD().listarFiliais(true);
        Filial filial = Conexao.filial;
        filiais.remove(filial);
        for (Filial f : filiais) {
            Conexao.filial = f;
            clientes.add(new ClienteBD().getClienteByNome(nome, true));
        }
        Conexao.filial = filial;
        return clientes;
    }

    @Override
    public Cliente buscarCliente(Integer id, Integer identificadorSede) {
        Cliente cliente = null;
        
        List<Filial> filiais = new FilialBD().listarFiliais(true);
        for (Filial f : filiais) {
            if (f.getId().equals(identificadorSede))
            cliente = new ClienteBD().getClienteByID(id, true);
        }
        
        return cliente;
    }
}
