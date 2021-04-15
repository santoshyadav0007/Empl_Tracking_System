

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.Emp;
import help.Well;


@WebServlet("/add")
public class SrvL_add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pwriter = response.getWriter();
		
		
		int id=Integer.parseInt(request.getParameter("emp_id"));
		 String pass=request.getParameter("emp_pass");
		 String name=request.getParameter("emp_name");
		 String mail=request.getParameter("emp_mail");
		 String dep=request.getParameter("emp_dep");
		 	
		 
		 
		 
		 Emp e=new Emp();
		 e.setDep(dep);e.setEmail(mail);
		 e.setName(name);e.setPass(pass);e.setId(id);
		 
		 int stts=Well.addNewEmp(e);
		 if(stts==0) {
			 String col="color:black";
			 String msg="Email or Id already Exists";
			 pwriter.print("<center><b><h2 style="+col+">"+msg+"</h2></b></center>");
			 RequestDispatcher dis=request.getRequestDispatcher("addNewEmp.jsp");
			 dis.include(request, response);
			 }
		 else
		 response.sendRedirect("empList");
	}

}
