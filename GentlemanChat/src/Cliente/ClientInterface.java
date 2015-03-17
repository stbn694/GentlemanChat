package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface ClientInterface extends Remote {
    
    public HashMap getFriends() throws RemoteException;
    public void SendMessage(String text) throws RemoteException;
    public boolean SendPeticion(String idPeticion) throws RemoteException;
}
