package multinacional;

import dao.ClienteBD;
import dao.CompraBD;
import dao.Conexao;
import dao.ProdutoBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Filial;
import model.Produto;

public class SedeLocal extends UnicastRemoteObject implements SedeInter {

    public SedeLocal() throws RemoteException {
        super();
    }

    @Override
    public void criarCliente(Cliente cliente) {
        new ClienteBD().novoCliente(cliente, Boolean.TRUE);
    }

    @Override
    public List<Cliente> buscarClienteNome(String nome) {
        List<Cliente> clientes = new ArrayList<>();
        ClienteBD clienteBD = new ClienteBD();
        if (clienteBD.getClienteByNome(nome, Boolean.TRUE) != null)
            clientes.add(clienteBD.getClienteByNome(nome, Boolean.TRUE));
        
        Filial filial = Conexao.filial;
        List<Filial> filiais = new ArrayList<>();
        try {
            filiais = new MultinacionalServidorCentral().listarFiliais();
            filiais.remove(filial);
            for (Filial f : filiais) {
                Conexao.filial = f;
                if (clienteBD.getClienteByNome(nome, Boolean.TRUE) != null)
                    clientes.add(clienteBD.getClienteByNome(nome, Boolean.TRUE));
            }
        } catch (RemoteException ex) {
            System.out.println("Falha no conexão remota com o servidor... Alexandrice");
        }
        Conexao.filial = filial;
        return clientes;
    }

    @Override
    public Cliente buscarCliente(Integer id, String identificadorSede) {
        Cliente cliente = null;
        
        List<Filial> filiais;
        try {
            filiais = new MultinacionalServidorCentral().listarFiliais();
            for (Filial f : filiais) {
                if (f.getCodigo().equals(identificadorSede))
                    cliente = new ClienteBD().getClienteByID(id, Boolean.TRUE);
            }
        } catch (RemoteException ex) {
            System.out.println("Falha no conexão remota com o servidor... Alexandrice");
        }
        
        return cliente;
    }

    @Override
    public void cadastrarProduto(Produto produto) throws RemoteException {
        new ProdutoBD().novoProduto(produto, Boolean.TRUE);
    }

    @Override
    public List<Produto> listarProdutos() throws RemoteException {
        return new ProdutoBD().listarProdutos(Boolean.TRUE);
    }

    @Override
    public void novaCompra(Compra compra) throws RemoteException {
        new CompraBD().novaCompra(compra, Boolean.TRUE);
    }

    @Override
    public List<Compra> listarComprasFilial() throws RemoteException {
        return new CompraBD().listarComprasFilial(Boolean.TRUE);
    }

    @Override
    public List<Compra> listarComprasCliente(Cliente cliente) throws RemoteException {
        return new CompraBD().listarComprasCliente(cliente, Boolean.TRUE);
    }
}
