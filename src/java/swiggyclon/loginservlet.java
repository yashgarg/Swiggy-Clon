package swiggyclon;


import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author hp
 */
public class loginservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        String uname = request.getParameter("usrname");
         String pswd = request.getParameter("pass");
         String remember=request.getParameter("remember");
        try (PrintWriter out = response.getWriter()) {     
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false","root","yashgarg");  
            PreparedStatement stmt = conn.prepareStatement("Select UserName,Password from appuser");   
            ResultSet rs = stmt.executeQuery();  
            int flag = 0;
            while(rs.next())  {
                String r=rs.getString("UserName");
                String s=rs.getString("Password");
                
                if(uname.equals(r))
               {
                if(pswd.equals(s))
                   {
                       flag=1;
                    }
                }
}
HttpSession session=request.getSession();
if(flag ==1)
{
    
if(remember!=null&&session!=null)
{
    session.setMaxInactiveInterval(60*60*24*15);
   // String url=response.encodeRedirectURL("index.html");
    response.sendRedirect("index.html"); 
}
}
else
{
    out.println("Login unsuccessful");
}

conn.close();  

}
       catch(Exception e)
       {
           out.println(e);
       }
        }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}


