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
    
    public ArrayList listaAmigos(String id){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            ArrayList<String> amigos = new ArrayList<String>();
            
            query = "SELECT idAmigo FROM Amigos where idUsuario=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                amigos.add(resultset.getString("idAmigo"));
            }
            
            return amigos;
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList peticionesAmistad(String id){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            ArrayList<String> peticiones = new ArrayList<String>();
            
            query = "SELECT idUsuario FROM Peticions where idPeticion=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                peticiones.add(resultset.getString("idUsuario"));
            }
            
            return peticiones;
            
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
    
    public void añadirPeticion(String id, String idPeticion){
        try {
            PreparedStatement statement = null;
            String query;
            
            query = "INSERT INTO Peticions VALUES (?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, idPeticion);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarPeticion(String id, String idPeticion){
        try {
            PreparedStatement statement = null;
            String query;
            
            query = "DELETE FROM Peticions WHERE idUsuario=? and idPeticion=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, idPeticion);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList buscarContactos(String contacto){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            ArrayList<String> contactos = new ArrayList<String>();
            
            query = "SELECT id FROM Usuarios WHERE id LIKE ? ";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, "%" + contacto + "%");
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                contactos.add(resultset.getString("id"));
            }
            
            if(!contactos.isEmpty()){
                return contactos;
            }
            else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean esAmigo(String id, String contacto){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            
            query = "SELECT idAmigo FROM Amigos where idUsuario=? and idAmigo=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, contacto);
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean esPeticion(String id, String contacto){
        try {
            ResultSet resultset = null;
            PreparedStatement statement = null;
            String query;
            
            query = "SELECT idPeticion FROM Peticions where idUsuario=? and idPeticion=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, contacto);
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(BDAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
