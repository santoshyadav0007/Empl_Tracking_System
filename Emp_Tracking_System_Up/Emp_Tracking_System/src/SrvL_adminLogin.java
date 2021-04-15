import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * Servlet implementation class SrvL1
 */
@WebServlet("/adminverify")
public class SrvL_adminLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static int inn=0;

	public static boolean validate(String aname,String apass) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        CallableStatement stmt = null;
        
        //ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "empData";
        String driver = "com.mysql.cj.jdbc.Driver";  
        String userName = "noy";  
        String password = "passwd"; 

        try {  
            Class.forName(driver);  
            conn = DriverManager.getConnection(url + dbName, userName, password);  
            
            ps=conn.prepareStatement("select check_admin(?,?)");
              ps.setString(1,aname);ps.setString(2,apass);
              ResultSet rs=ps.executeQuery();
            
            if(rs.next() && rs.getInt(1)==1){
            	return true;
            }
            
            conn.close();
            

        } catch (Exception e) {
        	String ex=e.toString();
        	if(ex.substring(29, 52).equals("CommunicationsException")) {
        		System.out.println("!!!!!!! How many times do I need to remind you that you need to start mysql server first!!!!!!!");  
        		inn=1;
        					}
        	}
        return false;
    }  // eo validate
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	
		
		//---------------------------------
		
		 response.setContentType("text/html");
		 PrintWriter pwriter = response.getWriter();
		
		 
		 String uname=request.getParameter("email");
		 String pass=request.getParameter("password");
		 
		 HttpSession ses=request.getSession();
		 String admin=ses.getAttribute("admin")+"";
		 if(!admin.equals("null")) {
			 RequestDispatcher dis=request.getRequestDispatcher("adminHome.jsp");
			 dis.forward(request, response);
		 						}
		 else {
		 
		 if(validate(uname,pass)){
			 ses.setAttribute("admin",uname);
		 RequestDispatcher dis=request.getRequestDispatcher("adminHome.jsp");
		 dis.forward(request, response);
			 //pwriter.print("");
			 
		 }
		 else
		 {
		 	//pwriter.println("<script type=\"text/javascript\">");
		 	//pwriter.println("alert('Invalid username or password !')");
		 	//pwriter.println("location='home.html';");
		 	//pwriter.println("</script>");
		 
			
			if(inn==1) {
				inn=0;
				String rude="Ask your Dumb coder to turn-on the database server first";
				String war="Please turn-on the database-Server";
				pwriter.println("<script type=\"text/javascript\">");
				pwriter.println("alert('"+war+"')");
				pwriter.println("location='adminLogin.jsp';");
				pwriter.println("</script>");
			}
			 
		 else {   // fun
			 String col="color:#FFFFFF";
				String stts="Username or password is incorrect !";
	 pwriter.print("<center><b><h2 style="+col+">"+stts+"</h2></b></center>");
		 RequestDispatcher dis=request.getRequestDispatcher("adminLogin.jsp");
		 dis.include(request, response);
		 
		 	}//eo fun
		 
		 }
		 

		 }

		 
		 
		 
	}
	


}
