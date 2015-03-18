/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esteban Gerpe
 */
public class Servidor {
    
    public static void main(String[] args) throws MalformedURLException {
        Registry registry;
        int RMIPortNum = 1099;
        try {
            registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.createRegistry(RMIPortNum);
            } catch (RemoteException ex1) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        ServerImpl exportedObj = null;
        try {
            exportedObj = new ServerImpl();
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String registryURL = "rmi://localhost:" + Integer.toString(RMIPortNum) + "/callback";
        
        try {
            Naming.rebind(registryURL, exportedObj);
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
