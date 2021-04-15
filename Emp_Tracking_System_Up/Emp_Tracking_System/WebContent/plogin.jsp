<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="help.*" %>
<!DOCTYPE html>
<html>
<head>

<title>Login</title>

<script>
	function areEmpty(form){
		if(form.Emp_mail.value.length==0){
			alert("Enter username!");
			form.Emp_mail.focus();
			return false;
		}

		if(form.Password.value.length==0){
			alert("Enter password");
			form.Password.focus();
			return false;
		}

		return true;
	}
</script>

</head>

<body style="background-color:#3381C1;">
<%String mail=session.getAttribute("una")+"";if(!mail.equals("null")){response.sendRedirect("welcome.jsp");} %>

<a href="welcome.jsp" style="float:left">Back</a>

<center>
	<h1 style="color:black">Login</h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.Syu9MPmAwcPpN4WZuT8iVQHaH0%26pid%3DApi&f=1"/>
	<br/><br/>
	<form method="post"  action="verify" onsubmit="return areEmpty(this)">
		<b>Emp mail <input type="email" name="Emp_mail"/></b><br/><br/>
		<b>Password <input type="password" name="Password"/></b><br/><br/>
		<center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Login"/></center>
	</form>
	
	<form><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="submit" formaction="signUp.html">Create an Account</button>
	</form>
	<br/>
	</center>
	
</body>
</html>