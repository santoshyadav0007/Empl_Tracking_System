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
import help.work;


@WebServlet("/assign")
public class SrvL_assign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pwriter = response.getWriter();
		
		
		int id=Integer.parseInt(request.getParameter("emp_id"));
		 
		 String name=request.getParameter("emp_name");
		 String pname=request.getParameter("project_name");
		 String role=request.getParameter("role_name");
		 String perf=request.getParameter("perf");
		 
		 
		 
		 work e=new work();
		 e.setPname(pname);e.setRole(role);
		 e.setPerf(perf);e.setId(id);
		 
		 Well.assign(e);
		 
		 
		
		 response.sendRedirect("workList");
	}

}
