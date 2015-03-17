package Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface{
    
    private Chat chat;
    private String id;
    private String name;
    private HashMap<String,ClientInterface> friends;
    
    public ClientImpl(String id, String name) throws RemoteException{
        this.id = id;
        this.name = name;
        this.chat = null;
        this.friends = null;
    }
    
    @Override
    public void SendMessage(String text) throws RemoteException{
        if( chat == null){
            chat = new Chat();
            chat.setLocationRelativeTo(null);
            chat.setResizable(false);
            chat.setVisible(true);
        }
        chat.getjTextArea1().append(this.name+": "+text+"\n");
    }
    
    public HashMap getFriends() throws RemoteException{
        return friends;
    }
    
    public boolean SendPeticion(String idPeticion) throws RemoteException{
        //Aqui o servidor lanzaria o panel no cliente preguntando se acepta a peticion
        return false;
    }
}
