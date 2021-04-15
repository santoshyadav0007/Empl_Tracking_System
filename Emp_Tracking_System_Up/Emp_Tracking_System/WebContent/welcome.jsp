<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="help.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Tracking System</title>

<style type="text/css">
  
  form{
  font-family: sans-serif;
  font-size: 30px;
  float:right;
  margin-right: 30px;
  padding: 15px;
  
  
}

button{
height:1;
border-color:transparent;
font-size: 25px;
font-weight: bold;
color:white;
background:#063247;
}

nav button:hover,
nav button.active{
  color: #23dbdb;
}

body{
  font-family: sans-serif;
  overflow: hidden;
  user-select: none;
}
nav .logo{
  color: white;
  font-size: 33px;
  font-weight: bold;
  line-height: 70px;
  padding-left: 110px;
}
nav{
  height: 70px;
  background: #063247;
  box-shadow: 0 3px 15px rgba(0,0,0,.4);
}
nav ul{
  float: right;
  margin-right: 30px;
}
nav ul li{
  display: inline-block;
}
nav ul li a{
  color: white;
  display: block;
  padding: 0 15px;
  line-height: 70px;
  font-size: 20px;
  background: #063247;
  transition: .5s;
}

nav ul ul{
  position: absolute;
  top: 85px;
  border-top: 3px solid #23dbdb;
  opacity: 0;
  visibility: hidden;
}
nav ul li:hover > ul{
  top: 70px;
  opacity: 1;
  visibility: visible;
  transition: .3s linear;
}
nav ul ul li{
  width: 150px;
  display: list-item;
  position: relative;
  border: 1px solid #042331;
  border-top: none;
}
nav ul ul li a{
  line-height: 50px;
}
nav ul ul ul{
  border-top: none;
}
nav ul ul ul li{
  position: relative;
  top: -70px;
  left: 150px;
}
nav ul ul li a i{
  margin-left: 45px;
}
section{
  background: url("https://uploads-ssl.webflow.com/5c7e3d44d0f1dfa662c442d0/5dea67c1fdae7b668485b07a_How_Much_Does_Employee_Monitoring_Software_Cost_.png");
  background-position: center;
  background-size: cover;
  height: 100vh;
}
  
  
  </style>

</head>
<body>



<%String mail=session.getAttribute("una")+"";%>
<%String admin=session.getAttribute("admin")+"";%>

<nav>
  <label class="logo">Track The Employee</label>
  
		<form method="post">
			<%if(mail.equals("null")){ %>
			<button type="submit" formaction="login.jsp">Login</button>
			<%}else{ %>
			<%Emp e=Well.getEmployeeByEmail(mail); %>
			<button type="submit" formaction="verify">Login (<%=e.getName() %>)</button>
			<%} %>
			
			<button type="submit" formaction="signUp.jsp">SignUp</button>
			
			<%if(admin.equals("null")){ %>
			<button type="submit" formaction="adminLogin.jsp">Admin</button>
			<%}else{ %>
			<button type="submit" formaction="adminverify">Admin(*)</button>
			<%} %>
		</form>
  
  </nav>
  <section></section>
	<!-- #272B33  #012E43 -->
	<!-- http://asgstrategies.com/wp-content/uploads/2015/06/choosing-the-right-employee.jpg -->
	<!--<img src="https://uploads-ssl.webflow.com/5c7e3d44d0f1dfa662c442d0/5dea67c1fdae7b668485b07a_How_Much_Does_Employee_Monitoring_Software_Cost_.png"/>-->

	
</body>
</html>