import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/adminout")
public class SrvL_adminLogout extends HttpServlet {
	
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	
	//request.getRequestDispatcher("login.html").include(request, response);
	
	HttpSession session=request.getSession();
	String admin=session.getAttribute("admin")+"";
	if(!admin.equals("null")) {
	//System.out.println("out "+mail);
	session.invalidate();
	out.println("<script type=\"text/javascript\">");
	out.println("alert('Successfully logged out');");
	out.println("location='adminLogin.jsp';");
	out.println("</script>");
		
	}else {response.sendRedirect("adminLogin.jsp");}
	//RequestDispatcher dis=request.getRequestDispatcher("adminHome.jsp");
	//dis.forward(request, response);
	
	//Cookie ck=new Cookie("uname","");
	//ck.setMaxAge(0);
	//response.addCookie(ck);
	
	
}
}