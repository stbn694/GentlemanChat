package Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface, ClientToClient{
    private String id;
    private String name;
    private String password;
    private HashMap<String,ClientToClient> friends;
    private HashMap<String,Chat> chats;
    private ArrayList<String> requests;
    private Aplication aplication;
    
    public ClientImpl(String id, String password) throws RemoteException{
        this.id = id;
        this.password = password;
        this.friends = null;
        this.chats = new HashMap<String,Chat>();
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
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

    public void setAplication(Aplication aplication) {
        this.aplication = aplication;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public Aplication getAplication() {
        return aplication;
    }
        

    public void SendMessage(ClientToClient friend, String text) throws RemoteException{
        if (!this.chats.containsKey(friend.getId())) {
            Chat chat = new Chat(friend, this);
            this.chats.put(friend.getId(), chat);
            chat.setLocationRelativeTo(null);
            chat.setResizable(false);
            chat.setVisible(true);
        }
        if (text.equals("zumbido")) {
            Integer x_inicial = this.chats.get(friend.getId()).getX();
            Integer y_inicial = this.chats.get(friend.getId()).getY();
            Integer width_inicial = this.chats.get(friend.getId()).getWidth();
            Integer heigth_inicial = this.chats.get(friend.getId()).getHeight();
            for(Integer i = 0; i < 20; i++) {
                try {
                    this.chats.get(friend.getId()).setBounds(x_inicial + 10, y_inicial + 10, width_inicial, heigth_inicial);
                    Thread.sleep(10);
                    this.chats.get(friend.getId()).setBounds(x_inicial - 10, y_inicial - 10, width_inicial, heigth_inicial);
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.chats.get(friend.getId()).setBounds(x_inicial, y_inicial, width_inicial, heigth_inicial);
        }
        else {
            this.chats.get(friend.getId()).getjTextArea1().append(friend.getId() + ":  " + text + "\n");
        }
    }
    
    public void SendPeticion(String idPeticion) throws RemoteException{
        this.requests.add(idPeticion);
        this.aplication.getjLabel2().setVisible(true);
        this.aplication.getjLabel3().setText(Integer.toString(this.requests.size()));
        this.aplication.getjLabel3().setVisible(true);
    }
    
    public void añadirAmigoConectado(ClientInterface c) throws RemoteException{
        if(!this.friends.containsKey(c.getId())){
            this.friends.put(c.getId(), (ClientToClient) c);
            this.aplication.getFriendList().addElement(c.getId());
        }
        
    }
    
    public void eliminarAmigoConectado(ClientInterface c) throws RemoteException{
        if(this.friends.containsKey(c.getId())){
            this.friends.remove(c.getId());
            this.aplication.getFriendList().removeElement(c.getId());
        }
 
    }
}
