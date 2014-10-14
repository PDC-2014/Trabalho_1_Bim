package multinacional;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Produto;

public interface SedeInter extends Remote{
    List<Cliente> buscarClienteNome(String nome) throws RemoteException;
    Cliente buscarClienteCod(String cod, String identificadorSede) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    void novaCompra(Compra compra) throws RemoteException;
    List<Compra> listarComprasFilial() throws RemoteException;
    List<Compra> listarComprasCliente(Cliente cliente) throws RemoteException;
}
