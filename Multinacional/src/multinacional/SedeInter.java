package multinacional;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Cliente;

public interface SedeInter extends Remote{
    void criarCliente() throws RemoteException;
    List<Cliente> buscarClienteNome(String nome) throws RemoteException;
    Cliente buscarCliente(Integer id, String identificadorSede) throws RemoteException;
}
