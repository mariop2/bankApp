<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styles.css">
    <title>ABC Bank</title>
    <%  session.setAttribute("value", "custMenu"); %>

</head>
<body>
<h1>Customer Main Menu!!!!</h1>
	<form name = "custMenu" action="webServlet" method ="post" class="modal-content">
	<table>
		<tr>
			<td><input type="submit" value="Show Customers" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Customer" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Update Customer" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Delete Customer" name="sub"></td>
		</tr>
		
	</table>
</form>
</body>
</html>