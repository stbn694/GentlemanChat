/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/* 
    Igual para alguns traballos era bo delegar en fios.
*/
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private BDAccess database;
    private HashMap<String,ClientInterface> connectUsers;

    public ServerImpl() throws RemoteException {
        this.database = new BDAccess();
        this.connectUsers = new HashMap<String,ClientInterface>();
    }
    
    public HashMap login(String id, String password,ClientInterface cliente) throws RemoteException{
        if(this.database.comprobarUsuario(id,password)){
            ArrayList<String> peticiones = this.database.peticionesAmistad(id);
            
            if(peticiones != null){
                for(String idPeticion : peticiones){
                    if(cliente.SendPeticion(idPeticion)){
                        this.database.añadirAmigo(id, idPeticion);
                    }
                }
            }
            
            HashMap <String,ClientInterface> friends = new HashMap <String,ClientInterface>();
            
            for(String idAmigo : this.connectUsers.keySet()){
                if(this.database.comprobarAmigo(id,idAmigo)){
                    friends.put(idAmigo, this.connectUsers.get(idAmigo));
                }
            }
            return friends;
        }
        return null;
    }
    
    public void logout(String id) throws RemoteException{
        this.connectUsers.remove(id);
    }
    
    public void peticionAmistad(String id,String idPeticion) throws RemoteException{
        if(this.connectUsers.containsKey(idPeticion)){
            if(this.connectUsers.get(idPeticion).SendPeticion(id)){
                this.database.añadirAmigo(id, idPeticion);
                
                this.connectUsers.put(id, this.connectUsers.get(idPeticion));
                this.connectUsers.put(idPeticion, this.connectUsers.get(id));
            }
        }
        else{
            this.database.añadirPeticion(id, idPeticion);
        }
    }
    
    public void eliminarAmigo(String id,String idAmigo) throws RemoteException{
        /*
        Deberia ser o servidor quen borre o amigo da lista do cliente? 
        Ou deberia ser o cliente despois de invocar a funcion quen a borre?
        Deime de conta que o profesor dixonos que so tiña que mostrar a interfaz os amigos conectados a aplicación.
        Como borrarias os que non estan conectados? Consideramos que esperas a que os vexas conectados?
        */
        if(this.connectUsers.containsKey(idAmigo)){
            this.database.eliminarAmigo(id, idAmigo);
            this.connectUsers.get(id).getFriends().remove(idAmigo);
        }
    }
}
