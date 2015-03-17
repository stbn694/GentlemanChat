/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author xurxo
 */
public interface ServerInterface extends Remote {
    
    public HashMap login(String id, String password,ClientInterface cliente) throws RemoteException;
    public void logout(String id) throws RemoteException;
    public void peticionAmistad(String id,String idPeticion) throws RemoteException;
    public void eliminarAmigo(String id,String idAmigo) throws RemoteException;
}
