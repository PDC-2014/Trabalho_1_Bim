package multinacional;

import java.rmi.Naming;

public class Main {

    public static void main(String[] args) {
        subirServidor();
        subirSedes();
    }
    
    public static void subirServidor() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1095);
            MultiNacionalInter multi = new MultinacionalServidorCentral();
            Naming.rebind(util.Util.rmiServidor, multi);
            System.out.println("Servidor on - Multinacional");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subirSedes() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1096);
            java.rmi.registry.LocateRegistry.createRegistry(1097);
            java.rmi.registry.LocateRegistry.createRegistry(1098);
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            SedeInter sede = new SedeLocal();
            Naming.rebind(util.Util.rmiSede1, sede);
            Naming.rebind(util.Util.rmiSede2, sede);
            Naming.rebind(util.Util.rmiSede3, sede);
            Naming.rebind(util.Util.rmiSede4, sede);
            System.out.println("Servidor on - Sedes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
