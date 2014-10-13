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
   
   private FilialBD filialBD = new FilialBD();

    @Override
    public List<Filial> listarFiliais() throws RemoteException {
        return filialBD.listarFiliais(Boolean.TRUE);
    }

    @Override
    public Filial conectarFilial() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
