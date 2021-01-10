<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styles.css">
    <title>Teller Portal</title>
    <%  session.setAttribute("value", "tellerMenu"); %>

</head>
<body>
<h1>Teller Main Menu</h1>
	<form name = "tellerMenu" action="webServlet" method ="post" class="modal-content">
	<table>
		<tr>
			<td><input type="submit" value="Delete Customers" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Display Transactions" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Make Transaction" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Logout" name="sub"></td>
		</tr>
	</table>
</form>
</body>
</html>