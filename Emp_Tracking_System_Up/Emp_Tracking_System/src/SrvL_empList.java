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
@WebServlet("/empList")  
public class SrvL_empList extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        
        HttpSession ses=request.getSession();
		String admin=ses.getAttribute("admin")+"";
        
		if(!admin.equals("null")) {
        out.println("<a href='addNewEmp.jsp'>Add New Employee</a>");  
        out.println("<h1>Employees List</h1>");  
          
        List<Emp> list=Well.getAllEmployees();  
          
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>UserName</th><th>Password</th><th>Name</th><th>Id</th><th>Department</th><th>Mobile</th><th>Address</th></tr>");  
        for(Emp e:list){
         out.print("<tr><td>"+e.getEmail()+"</td><td>"+e.getPass()+"</td><td>"+e.getName()+"</td><td>"+
        e.getId()+"</td><td>"+e.getDep()+"</td><td>"+e.getMobile()+"</td><td>"+e.getAddr()+"</td><td><a href='adminEmpEdit.jsp?upath="+e.getEmail()+"'>edit</a></td>  <td><a href='delete?id="+e.getId()+"'>delete</a></td></tr>");  
        }  
        out.print("</table>");
        out.print("<a href='adminHome.jsp'>Back</a>");
        
		}
		
		else {response.sendRedirect("adminLogin.jsp");}
          
        out.close();  
    }  
}  