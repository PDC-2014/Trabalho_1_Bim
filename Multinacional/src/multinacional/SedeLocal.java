package multinacional;

import static dao.ClienteBD.getClienteByCod;
import static dao.ClienteBD.getClienteByNome;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Compra;
import model.Filial;
import model.Produto;
import dao.Conexao;

@SuppressWarnings("serial")
public class SedeLocal extends UnicastRemoteObject implements SedeInter {

	public SedeLocal() throws RemoteException {
		super();
	}

	@Override
	public List<Cliente> buscarClienteNome(String nome) {

		List<Cliente> clientes = new ArrayList<>();

		if (getClienteByNome(nome) != null)
			clientes.add(getClienteByNome(nome));

		Filial filial = Conexao.filial;
		List<Filial> filiais = new ArrayList<>();
		try {
			filiais = new MultinacionalServidorCentral().listarFiliais();
			filiais.remove(filial);
			for (Filial f : filiais) {
				Conexao.filial = f;
				if (getClienteByNome(nome) != null)
					clientes.add(getClienteByNome(nome));
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}
		Conexao.filial = filial;
		return clientes;
	}

	@Override
	public Cliente buscarClienteCod(String cod, String identificadorSede) {
		Cliente cliente = null;

		List<Filial> filiais;
		try {
			filiais = new MultinacionalServidorCentral().listarFiliais();
			for (Filial f : filiais) {
				if (f.getCodigo().equals(identificadorSede))
					cliente = getClienteByCod(cod);
			}
		} catch (RemoteException exception) {
			exception.printStackTrace();
		}

		return cliente;
	}

	@Override
	public List<Produto> listarProdutos() throws RemoteException {
		return listarProdutos();
	}

	@Override
	public void novaCompra(Compra compra) throws RemoteException {
		try {
			novaCompra(compra);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Compra> listarComprasFilial() throws RemoteException {
		return listarComprasFilial();
	}

	@Override
	public List<Compra> listarComprasCliente(Cliente cliente) throws RemoteException {
		return listarComprasCliente(cliente);
	}
}
