package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote { 
    public String getId() throws RemoteException;
    public String getPassword() throws RemoteException;
    public boolean SendPeticion(String idPeticion) throws RemoteException;
    public void añadirAmigoConectado(ClientInterface c) throws RemoteException;
    public void eliminarAmigoConectado(ClientInterface c) throws RemoteException;
}
