/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xurxo
 */
public class BDAccess {
    private Connection connection;

    public BDAccess() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gentlemanchat", "gentlemanchat", "gentlemanchat");
            } catch (SQLException ex) {
                Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean comprobarUsuario(String id,String password){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            
            query = "SELECT id,contrasinal FROM Usuarios where id=? and contrasinal=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                if(id.equals(resultset.getString("id")) && password.equals(resultset.getString("contrasinal"))){
                    return true;
                }
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean comprobarAmigo(String id, String idAmigo){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            
            query = "SELECT idUsuario,idAmigo FROM Amigos where idUsuario=? and idAmigo=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, idAmigo);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                if(id.equals(resultset.getString("idUsuario")) && id.equals(resultset.getString("idAmigo"))){
                    return true;
                }
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList peticionesAmistad(String id){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            ArrayList<String> peticiones = new ArrayList<String>();
            
            query = "SELECT idPeticion FROM Amigos where idUsuario=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                peticiones.add(resultset.getString("idPeticion"));
            }
            
            if(!peticiones.isEmpty()){
                return peticiones;
            }
            else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void añadirAmigo(String id, String idAmigo){
        try {
            PreparedStatement statement = null;
            String query;
            
            query = "INSERT INTO Amigos VALUES (?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, idAmigo);
            statement.execute();
            
            statement = this.connection.prepareStatement(query);
            statement.setString(1, idAmigo);
            statement.setString(2, id);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarAmigo(String id, String idAmigo){
        try {
            PreparedStatement statement = null;
            String query;
            
            query = "DELETE FROM Amigos WHERE idUsuario=? and idAmigo=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, idAmigo);
            statement.execute();
            
            statement = this.connection.prepareStatement(query);
            statement.setString(1, idAmigo);
            statement.setString(2, id);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void añadirPeticion(String id, String idPeticion){
        try {
            PreparedStatement statement = null;
            String query;
            
            query = "INSERT INTO Peticions VALUES (?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, idPeticion);
            statement.setString(2, id);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
