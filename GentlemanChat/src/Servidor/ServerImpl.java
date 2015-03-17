/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.ClientInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author xurxo
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private HashMap<String,ClientInterface> connectUsers;
    public ServerImpl() throws RemoteException {
        
    }
    
    public HashMap login(String id, String password,ClientInterface cliente) throws RemoteException{
        HashMap <String,ClientInterface> friends = new HashMap <String,ClientInterface>();
        //Comprobar na base de datos
        connectUsers.put(id, cliente);
        
        return friends;
    }
}
