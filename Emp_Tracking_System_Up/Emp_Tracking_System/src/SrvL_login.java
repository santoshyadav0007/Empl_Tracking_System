import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse; 

/**
 * Servlet implementation class SrvL1
 */
@WebServlet("/verify")
public class SrvL_login extends HttpServlet {
	
	private static int inn=0;
	
	
	public static boolean validate(String uname,String pass) {
        
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
            stmt = conn.prepareCall("call isvalid_login(?,?,?)");
            stmt.setString(1,uname);stmt.setString(2,pass);stmt.registerOutParameter(3,java.sql.Types.INTEGER);
            //ps=conn.prepareStatement("select emp_id from empProfile where emp_uname=? and emp_pass=?");
              //ps.setString(1,uname);ps.setString(2,pass);
              //status=ps.executeUpdate();
            
            stmt.execute();
            //int stts=;
            //System.out.println(a);
            //System.out.println(a+" "+rs.getInt("emp_id"));
            if(stmt.getInt(3)==1){
            	return true;
            }
            //status=
            //pst.setString(2, pass);  
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
		 //Cookie gk[]=request.getCookies();
		 //if(ses!=null)System.out.println(ses.getAttribute("una"));
		 //if(ses.getAttribute("una"))

		 String mail=ses.getAttribute("una")+"";
		 if(!mail.equals("null")) {
			 RequestDispatcher dis=request.getRequestDispatcher("empHome.jsp");
			 dis.forward(request, response);
		 						}
		 else {
		 if(validate(uname,pass)){
			 
			 
			 ses.setAttribute("una",uname);
			//Cookie ck=new Cookie("uname",uname);//creating cookie object
			 //response.addCookie(ck);//adding cookie in the response
			 	
			 //response.sendRedirect("./empHome.jsp");
			 
			 //response.sendRedirect("./empHome.html"); 
		 RequestDispatcher dis=request.getRequestDispatcher("empHome.jsp");
		 dis.forward(request, response);
			 //pwriter.print("");
			 
		 }//eo inner if
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
					pwriter.println("location='login.jsp';");
					pwriter.println("</script>");
				}			
			 
				else {	 //fun
					String col="color:#FFFFFF";
					String stts="Username or password is incorrect !";
		 pwriter.print("<center><b><h2 style="+col+">"+stts+"</h2></b></center>");
		 RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
		 dis.include(request, response);
				} //eo fun
				
				}//eo inner else
		 
		 	
		 }//eo else		

		 
		 
	} 
	
	


}
