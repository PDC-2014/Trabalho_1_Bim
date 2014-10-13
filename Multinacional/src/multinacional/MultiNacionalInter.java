package multinacional;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Filial;

public interface MultiNacionalInter extends Remote{
	
    List<Filial> listarFiliais() throws RemoteException;
    Filial recuperarFilial(String codigo) throws RemoteException;
    
}
