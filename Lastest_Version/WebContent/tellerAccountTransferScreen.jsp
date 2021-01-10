<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% session.setAttribute("value", "tellerAccountTransferScreen"); %>

<div><h2>Transfer From:</h2></div>
<form action = "webServlet" method = "post">
	<div>Account Number : <%= session.getAttribute("accountId") %></div>
	<div>Type : <%= session.getAttribute("accountType") %></div>
	<div>Last Transaction : <%= session.getAttribute("accountLastUpdated") %></div>
	<div>Balance : <%= session.getAttribute("accountBalance") %></div>
	<br>
	<div><h2>Transfer To:</h2></div>
	<div>Account Number : <%= session.getAttribute("transferAccountId") %></div>
	<div>Type : <%= session.getAttribute("transferAccountType") %></div>
	<div>Last Transaction : <%= session.getAttribute("transferAccountLastUpdated") %></div>
	<div>Balance : <%= session.getAttribute("transferAccountBalance") %></div>
	<br>
	<div>Transfer Amount : <input type="text" name="transferAmount" placeholder = "Enter Transfer Amount" ></div>
	<br>
	<button type = "submit" name = "regBtns" value = "transfer">Transfer</button>
</form>
<hr>
<br>
<form name="Menuform" action="webServlet" method="post">
	<button type = "submit" name = "regBtns" value = "transactionHistory">View Account Transaction History</button>
	<button type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>


</body>
</html>