/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author xurxo
 */
public interface ServerInterface extends Remote {
    
    public HashMap login(ClientInterface c) throws RemoteException;
    public ArrayList devolverPeticiones(ClientInterface c) throws RemoteException;
    public void logout(ClientInterface c, ArrayList<String> friends) throws RemoteException;
    public void peticionAmistad(ClientInterface c,String idPeticion) throws RemoteException;
    public ArrayList buscarContactos(String contacto) throws RemoteException;
    public boolean esAmigo(ClientInterface c, String contacto) throws RemoteException;
    public boolean esPeticion(ClientInterface c, String contacto) throws RemoteException;
    public void añadirAmigo(ClientInterface c, String nAmigo) throws RemoteException;
    public void eliminarPeticion(ClientInterface c, String aEliminar) throws RemoteException;
}
