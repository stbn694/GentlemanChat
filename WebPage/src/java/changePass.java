/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xurxo
 */
public class changePass extends HttpServlet {
    
    @Override
    public void init() throws ServletException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(changePass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(changePass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(changePass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            
            HttpSession session = request.getSession(true);
            
        try {    
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daw", "gentlemanchat", "gentlemanchat");
            Statement statement = (Statement) conexion.createStatement();
            String query;
            
            query = "UPDATE usuarios SET contrasinal='"+newPass+"' WHERE id='"+session.getAttribute("session")+"'";
            statement.executeUpdate(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(changePass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
