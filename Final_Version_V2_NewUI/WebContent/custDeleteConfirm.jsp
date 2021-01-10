<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/web.css">
<title>Confirm Delete</title>
</head>
<body>
<% session.setAttribute("value", "custDeleteConfirm"); %>

<h3>Check Customer Info Below, And Verify If Information Changes Correctly:</h3>
<div>ID: <%= session.getAttribute("custDeleteID") %></div>
<div>Name: <%= session.getAttribute("custDeleteName") %></div>
<div>Status: <%= session.getAttribute("custDeleteStatus") %></div>
<div>Residence: <%= session.getAttribute("custDeleteCity") %>, <%= session.getAttribute("custDeleteState") %></div>

<form action = "webServlet" method = "post">
	<button class="button2" type="submit" name = "sub" value = "delete">Confirm Delete</button>
</form>
<div>----------------------------------</div>
<form action = "webServlet" method = "post">
	<button class="button2" type="submit" name = "sub" value = "inactive">Confirm Inactive</button>
	<button class="button2" type="submit" name = "sub" value = "mainmenu">Main Menu</button>
</form>

</body>
</html>