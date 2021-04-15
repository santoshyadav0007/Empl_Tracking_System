<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="help.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<style>

.foorm{
 position: relative;
  left:47cm;
  display: inline-block;
}

.navbar {
  overflow: hidden;
  background-color: #333;
  position: fixed; /* Set the navbar to fixed position */
  top: 0; /* Position the navbar at the top of the page */
  width: 100%; /* Full width */
}

/* Links inside the navbar */
.navbar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change background on mouse-over */
.navbar a:hover {
  background: #ddd;
  color: black;
}

/* Main content */
.main {
  margin-top: 30px; /* Add a top margin to avoid content overlay */
}

.dropbtn {
  background-color: #3498DB;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #2980B9;
}

.dropdown {
  position: relative;
  left:47cm;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  overflow: auto;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}
</style>



</head>
<body style="background-image:url('https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.2NN3QjJbvAfLLqAhjHEKOAEsCo%26pid%3DApi&f=1');
background-repeat: no-repeat;
	  background-attachment: fixed;
  background-size: cover;">
<!-- 
<nav>
  <a href="Emp List">Emp List</a> |
  <a href="Projects">Projects</a> |
  <a href="asd">Emp Leave</a> |
  <a href="python">Python</a>
</nav>
-->
<!--
<div class="navbar">
  <a href="#home">Profile</a>
  <a href="#news">My Shifts</a>
  <a href="#contact">My Leave</a>
  <a href="empLogout.jsp">logout</a>
  
</div>
	-->

<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>


<%String admin=session.getAttribute("admin")+"";%>
<% String formMail=request.getParameter("email"); %>

<%if(admin.equals("null")){response.sendRedirect("adminLogin.jsp");} %>





<div class="dropdown">
  <button onclick="myFunction()" class="dropbtn">Admin</button>
  <div id="myDropdown" class="dropdown-content">
    <a href="empList">Emp List</a>
    <a href="workList">Projects</a>
    
  </div>
</div>
	

  <br/><br/>

<div class="foorm">
<form  method="get" action="adminout">
	
	<input type="submit" value="Logout"/>
	
</form>
</div>



<%List<Emp> list=Well.getAllEmployees(); %>
<%//for(Emp w:list)System.out.println(w.getAddr()+w.getName()); %>



</body>
</html>