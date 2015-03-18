package Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface{
    private String id;
    private String name;
    private String password;
    private HashMap<String,ClientInterface> friends;
    
    
    public ClientImpl(String id, String name) throws RemoteException{
        this.id = id;
        this.name = name;
        this.friends = null;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    
    public void SendMessage(String text) throws RemoteException{
        //Cubrir
    }
    
    public boolean SendPeticion(String idPeticion) throws RemoteException{
        //Aqui o servidor lanzaria o panel no cliente preguntando se acepta a peticion
        return false;
    }
    
    public void añadirAmigoConectado(ClientInterface c) throws RemoteException{
        if(friends.containsKey(c.getId())){
            friends.put(c.getId(), c);
        }
    }
    
    public void eliminarAmigoConectado(ClientInterface c) throws RemoteException{
        if(friends.containsKey(c.getId())){
            friends.remove(c.getId());
        }
 
    }
}
