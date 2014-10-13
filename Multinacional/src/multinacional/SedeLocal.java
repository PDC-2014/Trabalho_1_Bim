package multinacional;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Cliente;

public class SedeLocal extends UnicastRemoteObject implements SedeInter{
    public SedeLocal() throws RemoteException{
        super();
    }

    @Override
    public void criarCliente() {
        
    }

    @Override
    public Cliente buscarClienteNome(String nome) {
        return null;
    }

    @Override
    public Cliente buscarCliente(String codigo, Integer identificadorSede) {
        return null;
    }
}
