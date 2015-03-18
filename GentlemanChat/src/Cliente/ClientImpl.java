package Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface, ClientToClient{
    private String id;
    private String name;
    private String password;
    private HashMap<String,ClientToClient> friends;
    private HashMap<String,Chat> chats;
    
    
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

    public void setFriends(HashMap<String, ClientToClient> friends) {
        this.friends = friends;
    }

    public HashMap<String, ClientToClient> getFriends() {
        return friends;
    }

    public HashMap<String, Chat> getChats() {
        return chats;
    }
    
    
    
    public void SendMessage(ClientToClient friend, String text) throws RemoteException{
        if (!this.chats.containsKey(friend.getId())) {
            Chat chat = new Chat(friend, this);
            this.chats.put(friend.getId(), chat);
            chat.setLocationRelativeTo(null);
            chat.setResizable(false);
            chat.setVisible(true);
        }
        this.chats.get(friend.getId()).getjTextArea1().append(friend.getId() + ":  " + text + "\n");
    }
    
    public boolean SendPeticion(String idPeticion) throws RemoteException{
        //Aqui o servidor lanzaria o panel no cliente preguntando se acepta a peticion
        return false;
    }
    
    public void añadirAmigoConectado(ClientInterface c) throws RemoteException{
        if(friends.containsKey(c.getId())){
            friends.put(c.getId(), (ClientToClient)c);
        }
    }
    
    public void eliminarAmigoConectado(ClientInterface c) throws RemoteException{
        if(friends.containsKey(c.getId())){
            friends.remove(c.getId());
        }
 
    }
}
