package multinacional;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Produto;

public interface SedeInter extends Remote{
    void criarCliente(Cliente cliente) throws RemoteException;
    List<Cliente> buscarClienteNome(String nome) throws RemoteException;
    Cliente buscarCliente(Integer id, String identificadorSede) throws RemoteException;
    void cadastrarProduto(Produto produto) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    void novaCompra(Compra compra) throws RemoteException;
    List<Compra> listarComprasFilial() throws RemoteException;
    List<Compra> listarComprasCliente(Cliente cliente) throws RemoteException;
}
