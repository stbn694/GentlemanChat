package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void SendMessage(String text) throws RemoteException;
}
