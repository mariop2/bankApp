<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/web.css">
<title>Insert title here</title>
</head>
<body>

<% session.setAttribute("value", "tellerAccountScreen"); %>

<div><h2>Verify Information Below</h2></div>
<form action = "webServlet" method = "post">
	<div>Account Number : <%= session.getAttribute("accountId") %></div>
	<div>Type : <%= session.getAttribute("accountType") %></div>
	<div>Last Transaction : <%= session.getAttribute("accountLastUpdated") %></div>
	<div>Balance : <%= session.getAttribute("accountBalance") %></div>
	<br>
		<button class="button2" type = "submit" name = "regBtns" value = "transactionHistory">View Account Transaction History</button>
		<br>
	<hr>
</form>
<form action = "webServlet" method = "post">
	<div><h3>Withdraw or Deposit</h3></div>
	<div>Deposit/Withdraw Amount : <input type="text" name="DepositorWithdrawAmount" placeholder = "Enter Amount"></div>
	<br>
	<button class="button2"  type = "submit" name = "regBtns" value = "deposit">Deposit</button>
	<button class="button2"  type = "submit" name = "regBtns" value = "withdraw">Withdraw</button>
</form>

<hr>

<div><h3>Transfer Funds To Another Account</h3></div>
<form action = "webServlet" method = "post">
	<div>Transfer From (Account Number) : <%= session.getAttribute("accountId") %></div>
	<div class = "flexbox">
	        <div class = "searchnav">Transfer To: 
	            <input type="number" placeholder="Enter Account Number" name="trasnferAccountId">
	            <br>
	        </div>
	</div>
	<br>
	<button class="button2"  type = "submit" name = "regBtns" value = "transferSearch">Begin Transfer</button>
</form>
<hr>
<br>
<form name="Menuform" action="webServlet" method="post">
	<button class="button2"  type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>


</body>
</html>