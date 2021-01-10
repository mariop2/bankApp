<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styles.css">
    <link rel="stylesheet" href="./css/web.css">
    <title>Teller Portal</title>
    <%  session.setAttribute("value", "tellerMenu"); %>

</head>
<body>
<h1>Teller Main Menu</h1>
<div class="topnav">
  			<a class="active" href="tellerMenu.jsp">Home</a>
  			<a class="active" href="tellerAccountSearch.jsp">Make Transaction</a>
		  <a class="active" href="homeLogin.jsp">Logout</a>
  	  </div>
	<form name = "tellerMenu" action="webServlet" method ="post" class="modal-content">
<!-- 	<table>
		<tr>
			<td><input type="submit" value="Make Transaction" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="logout" name="sub"></td>
		</tr>
	</table> -->
	<div class="imgcontainer">
	    <img src="images/login.png" alt="Avatar" class="avatar">
	  </div>
</form>
</body>
</html>