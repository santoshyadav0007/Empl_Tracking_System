import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;  
import help.*;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  
@WebServlet("/workList")  
public class SrvL_workList extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        
        HttpSession ses=request.getSession();
		String admin=ses.getAttribute("admin")+"";
        
		if(!admin.equals("null")) {
          
        out.println("<h1>On-going Projects</h1>");  
          
        List<work> wor=Well.getAllWorks();
        
          
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Name</th><th>Id</th><th>Project</th><th>Role</th><th>Performance</th></tr>");  
        for(work w:wor){
        	
         out.print("<tr><td>"+w.getName()+"</td><td>"+w.getId()+"</td><td>"+w.getPname()+"</td><td>"+w.getRole()+"</td><td>"+w.getPerf()+"</td><td><a href='assign.jsp?id="+w.getId()+"'>Assign</a></td></tr>");  
        }  
        out.print("</table>");
        out.print("<a href='adminHome.jsp'>Back</a>");
        
		}
		
		else {response.sendRedirect("adminLogin.jsp");}
          
        out.close();  
    }  
}  