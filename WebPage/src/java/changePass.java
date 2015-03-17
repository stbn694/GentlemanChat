
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class changePass extends HttpServlet {

    @Override
    public void init() throws ServletException {
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

        String email = (String) session.getAttribute("session");

        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gentlemanchat", "gentlemanchat", "gentlemanchat");
            PreparedStatement statement = null;
            String query;
            boolean equal = false;
            ResultSet resultset;

            query = "SELECT contrasinal FROM Usuarios WHERE id=?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, email);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                if (oldPass.equals(resultset.getString("contrasinal"))) {
                    equal = true;
                    query = "UPDATE Usuarios SET contrasinal=? WHERE id=?";
                    statement = conexion.prepareStatement(query);
                    statement.setString(1, newPass);
                    statement.setString(2, email);
                    statement.executeUpdate();

                    request.getRequestDispatcher("/session.jsp").forward(request, response);
                }
            }

            if(!equal){
                request.setAttribute("error", "-1");
                request.getRequestDispatcher("/session.jsp").forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(changePass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
