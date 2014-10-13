package multinacional;

import dao.FilialBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.Filial;

public class MultinacionalServidorCentral extends UnicastRemoteObject implements MultiNacionalInter{
   public MultinacionalServidorCentral() throws RemoteException{
        super();
    } 
   
    @Override
    public List<Filial> listarFiliais() throws RemoteException {
        return new FilialBD().listarFiliais(Boolean.TRUE);
    }
}
