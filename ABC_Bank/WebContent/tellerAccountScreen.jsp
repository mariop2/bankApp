<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% session.setAttribute("value", "tellerAccountScreen"); %>

<div><h3>Verify information below</h3></div>
<form action = "webServlet" method = "post">
	
	<div>Account Number : <%= session.getAttribute("accountId") %></div>
	<div>Type : <%= session.getAttribute("accountType") %></div>
	<div>Last Transaction : <%= session.getAttribute("accountLastUpdated") %></div>
	<div>Balance : <%= session.getAttribute("accountBalance") %></div>
	<div>Deposit/Withdraw Amount : <input type="text" name="DepositorWithdrawAmount" placeholder = <%= session.getAttribute("accountBalance") %>></div>
	
	<button type = "submit" name = "regBtns" value = "deposit">Deposit</button>
	<button type = "submit" name = "regBtns" value = "withdraw">Withdraw</button>

</form>

<form name="Menuform" action="webServlet" method="post">
	<button type = "submit" name = "regBtns" value = "transactionHistory">View Account Transaction History</button>
	<button type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>


</body>
</html>