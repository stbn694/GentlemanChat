/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.rmi.RemoteException;

/**
 *
 * @author xurxo
 */
public interface ClientToClient {
    public void SendMessage(String id, String text) throws RemoteException;
}
