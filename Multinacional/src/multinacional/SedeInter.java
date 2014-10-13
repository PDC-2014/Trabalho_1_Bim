package multinacional;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Cliente;

public interface SedeInter extends Remote{
    void criarCliente() throws RemoteException;
    Cliente buscarClienteNome(String nome) throws RemoteException;
    Cliente buscarCliente(String codigo, Integer identificadorSede) throws RemoteException;
}
