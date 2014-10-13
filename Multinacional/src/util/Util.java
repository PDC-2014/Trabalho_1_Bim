package util;

public class Util {
    public static final String rmiServidor = "rmi://localhost:1095/Multinacional";
    public static final String rmiSede1 = "rmi://localhost:1096/Sede1";
    public static final String rmiSede2 = "rmi://localhost:1097/Sede2";
    public static final String rmiSede3 = "rmi://localhost:1098/Sede3";
    public static final String rmiSede4 = "rmi://localhost:1099/Sede4";
    
    
    public static String definirFilial(String codigo){
        switch(codigo){
            case "S1": return "Server1";
            case "S2": return "Server2";
            case "S3": return "Server3";
            case "S4": return "Server4";
            default:return null;
        }
    }
}