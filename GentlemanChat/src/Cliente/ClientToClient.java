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
    public void SendMessage(ClientToClient friend, String text) throws RemoteException;
    public String getId() throws RemoteException;
}
