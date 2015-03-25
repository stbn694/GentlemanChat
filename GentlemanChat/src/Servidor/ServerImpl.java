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
            this.connectUsers.put(c.getId(), c);
            
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
    
    public ArrayList devolverPeticiones(ClientInterface c) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            ArrayList<String> peticiones = this.database.peticionesAmistad(c.getId());
            return peticiones;
        }
        return null;
    }
    
    public void logout(ClientInterface c, ArrayList<String> friends) throws RemoteException {
        this.connectUsers.remove(c.getId());
        
        for(String usuario : friends){
            this.connectUsers.get(usuario).eliminarAmigoConectado(c);
        }
    }
    
    public void peticionAmistad(ClientInterface c, String idPeticion) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            if(this.connectUsers.containsKey(idPeticion)){
                this.connectUsers.get(idPeticion).SendPeticion(c.getId());
            }    
            this.database.añadirPeticion(c.getId(), idPeticion);
        }
    }
    
    public ArrayList buscarContactos(String contacto) throws RemoteException {
        ArrayList<String> contactos = this.database.buscarContactos(contacto);
        
        return contactos;
    }
    
    public boolean esAmigo(ClientInterface c, String contacto) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            return this.database.esAmigo(c.getId(), contacto);
        }
        return false;
    }
    
    public boolean esPeticion(ClientInterface c, String contacto) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            return this.database.esPeticion(c.getId(), contacto);
        }
        return false;
    }
    
    public void añadirAmigo(ClientInterface c, String nAmigo) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            if (this.database.esPeticion(nAmigo, c.getId())) {
                this.database.añadirAmigo(c.getId(), nAmigo);
                if(this.connectUsers.containsKey(nAmigo)){
                    c.añadirAmigoConectado(this.connectUsers.get(nAmigo));
                    this.connectUsers.get(nAmigo).añadirAmigoConectado(c);
                }
                this.eliminarPeticion(c, nAmigo);
            }
        }
    }
    
    public void eliminarPeticion(ClientInterface c, String aEliminar) throws RemoteException {
        if (this.connectUsers.containsKey(c.getId())) {
            if (this.database.esPeticion(aEliminar, c.getId())) {
                this.database.eliminarPeticion(aEliminar, c.getId());
            }
        }
    }
}
