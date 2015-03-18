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
    
    public HashMap login(ClientInterface c) throws RemoteException{
        if(this.database.comprobarUsuario(c.getId(),c.getPassword())){
            ArrayList<String> peticiones = this.database.peticionesAmistad(c.getId());
            
            if(peticiones != null){
                for(String idPeticion : peticiones){
                    if(c.SendPeticion(idPeticion)){
                        this.database.añadirAmigo(c.getId(), idPeticion);
                    }
                }
            }
            
            HashMap <String,ClientInterface> friends = new HashMap <String,ClientInterface>();
            ArrayList<String> amigos = this.database.listaAmigos(c.getId());
            
            if(amigos.size() > 0){
                for(String idAmigo : amigos){
                    if(this.connectUsers.containsKey(idAmigo)){
                        friends.put(idAmigo, this.connectUsers.get(idAmigo));
                        this.connectUsers.get(idAmigo).añadirAmigoConectado(c);
                    }
                }
            }
            
            return friends;
        }
        return null;
    }
    
    public void logout(ClientInterface c) throws RemoteException{
        this.connectUsers.remove(c.getId());
        
        for(String usuario : this.connectUsers.keySet()){
            this.connectUsers.get(usuario).eliminarAmigoConectado(c);
        }
    }
    
    public void peticionAmistad(ClientInterface c, String idPeticion) throws RemoteException{
        if(this.connectUsers.containsKey(idPeticion)){
            if(c.SendPeticion(idPeticion)){
                this.database.añadirAmigo(c.getId(), idPeticion);
                
                this.connectUsers.get(idPeticion).añadirAmigoConectado(c);
                c.añadirAmigoConectado(this.connectUsers.get(c.getId()));
            }
        }
        else{
            this.database.añadirPeticion(c.getId(), idPeticion);
        }
    }
    
    public ArrayList buscarContactos(String contacto){
        ArrayList<String> contactos = this.database.buscarContactos(contacto);
        
        return contactos;
    }
}
