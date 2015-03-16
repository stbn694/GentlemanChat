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
public class login extends HttpServlet {
    
    @Override
    public void init() throws ServletException{
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            HttpSession session = request.getSession(true);
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/daw", "gentlemanchat", "gentlemanchat");
            Statement statement = (Statement) conexion.createStatement();
            ResultSet resultset;
            String query;
            
            query = "SELECT id FROM usuarios WHERE id='"+email+"'";
            resultset = statement.executeQuery(query);
            
            while(resultset.next()){
                if(resultset.getString("id").equals(email)){
                    session.setAttribute("session", email);
                    request.getRequestDispatcher("/session.jsp").forward(request, response);
                }
            }
            
            session.invalidate();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
