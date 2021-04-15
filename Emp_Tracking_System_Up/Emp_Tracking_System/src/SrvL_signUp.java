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

/**
 * Servlet implementation class SrvL1
 */
@WebServlet("/create")
public class SrvL_signUp extends HttpServlet {
	
	private static int inn=0;
	
	public static String validate(String uname,String pass,String name,int id,String dep) {
		String stts="";
		//if(uname.isEmpty()||pass.isEmpty()||name.isEmpty()||dep.isEmpty()||)
        Connection conn = null;
        PreparedStatement stmt = null;
        //CallableStatement stmt = null;
        
        //ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "empData";
        String driver = "com.mysql.cj.jdbc.Driver";  
        String userName = "noy";  
        String password = "passwd"; 
        //oracle.jdbc.driver.OracleDriver --> driver in Class.forName()    oracle.jdbc.OracleDriver
        // jdbc:oracle:thin:@localhost:1521:xe -->url
        try {  
            Class.forName(driver);  
            conn = DriverManager.getConnection(url + dbName, userName, password);  
            stmt = conn.prepareStatement("select check_acc(?,?,?,?,?)");
            stmt.setString(1,uname);stmt.setString(2,pass);stmt.setString(3,name);stmt.setInt(4,id);
            stmt.setString(5,dep);
            
            //stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
            //ps=conn.prepareStatement("select emp_id from empProfile where emp_uname=? and emp_pass=?");
              //ps.setString(1,uname);ps.setString(2,pass);
            ResultSet rs=stmt.executeQuery();rs.next();stts=rs.getString(1);
            conn.close();
            


        } catch (Exception e) {  
        	String ex=e.toString();
        	if(ex.substring(29, 52).equals("CommunicationsException")) {
        		System.out.println("!!!!!!! How many times do I need to remind you that you need to start mysql server first!!!!!!!");  
        		inn=1;
        					}  
        }
        return stts;
        
    }  // eo validate
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	
		
		//---------------------------------
		
		 response.setContentType("text/html");
		 PrintWriter pwriter = response.getWriter();
		 
		 String uname=request.getParameter("emp_mail");
		 String pass=request.getParameter("emp_pass");
		 String name=request.getParameter("emp_name");
		 int id=Integer.parseInt(request.getParameter("emp_id"));
		 String dep=request.getParameter("emp_dep");
		 
		 String stts=validate(uname,pass,name,id,dep);
		 if(stts.equals("created")){
			 
			 pwriter.println("<script type=\"text/javascript\">");
			 pwriter.println("alert('Account successfully created');");
			 pwriter.println("location='login.jsp';");
			 pwriter.println("</script>");
		 
			 //pwriter.print("");
			 
		 }
		 else
		 {
			 //do not
		 	//pwriter.println("<script type=\"text/javascript\">");
		 	//pwriter.println("alert('Invalid username or password !')");
		 	//pwriter.println("location='home.html';");
		 	//pwriter.println("</script>");#0EFF00
			 
			 if(inn==1) {
					inn=0;
					String rude="Ask your Dumb coder to turn-on the database server first";
					String war="Please turn-on the database-Server";
					pwriter.println("<script type=\"text/javascript\">");
					pwriter.println("alert('"+war+"')");
					pwriter.println("location='signUp.jsp';");
					pwriter.println("</script>");
				}
			 
			 else { //fun
			 
			 String c="color:#0EFF00";
		 pwriter.print("<center><h2 style="+c+">"+stts+"</h2></center>");
		 RequestDispatcher dis=request.getRequestDispatcher("signUp.jsp");
		 dis.include(request, response);
		 
			 	}//eo fun
		}
		 
	 
	}
	


}
