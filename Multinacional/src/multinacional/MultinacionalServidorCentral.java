package multinacional;

import dao.FilialBD;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import model.Filial;

public class MultinacionalServidorCentral extends UnicastRemoteObject implements MultiNacionalInter {
	
	private List<Filial> filiais = new ArrayList<Filial>();
	
	public MultinacionalServidorCentral() throws RemoteException {
		super();
	}

	private FilialBD filialBD = new FilialBD();

	@Override
	public List<Filial> listarFiliais() throws RemoteException {
		filiais.addAll(filialBD.listarFiliais(Boolean.TRUE));
		return filiais;
	}

	@Override
	public Filial recuperarFilial(String codigo) throws RemoteException {
		for (Filial filial : filiais) {
			if(filial.getCodigo().equals(codigo))
				return filial;
		}
		
		return filialBD.recuperarFilial(codigo, Boolean.TRUE);
	}

	
	
}
