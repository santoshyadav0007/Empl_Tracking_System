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

import help.Emp;
import help.Well;


@WebServlet("/update")
public class SrvL_update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pwriter = response.getWriter();
		
		
		int id=Integer.parseInt(request.getParameter("emp_id"));
		 String pass=request.getParameter("emp_pass");
		 String name=request.getParameter("emp_name");
		 String mobile=request.getParameter("emp_mobile");
		 String dep=request.getParameter("emp_dep");
		 String addr=request.getParameter("emp_addr");
		 
		 
		 
		 Emp e=new Emp();
		 e.setDep(dep);e.setAddr(addr);e.setMobile(mobile);
		 e.setName(name);e.setPass(pass);e.setId(id);
		 
		 Well.update(e);
		 
		 
		
		 response.sendRedirect("profile.jsp");
	}

}
