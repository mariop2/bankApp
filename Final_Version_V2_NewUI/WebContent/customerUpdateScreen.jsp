<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="css/web.css"> 
<script>
src = "../js/CustValidate.js"
function goBack() {
  window.history.back()
}
</script>
<meta charset="ISO-8859-1">
<title>Update Customer</title>
 <%  session.setAttribute("value", "customerUpdateScreen"); %>
</head>
<body>
 <form name="Customerform" action="webServlet" method="post">
  <div class="container">
    <h1>Update Customer Information</h1>
    <label for="ssn"><b>Customer SSN/ID : <%= session.getAttribute("CustomerUpdateId") %></b></label>
    <br>
    
    <label for="name"><b>Customer Name</b></label>
    <input type="text" placeholder="name" name="customerUpdateName" id="cname" pattern="[a-zA-Z][a-zA-Z ]{2,}" value = <%= session.getAttribute("CustomerUpdateName") %>>
    
<br>
    <label for="age"><b>Age</b></label>
    <input type="number" min="10" max="120" placeholder="Enter Age" name="customerUpdateAge" id="cage" value = <%= session.getAttribute("CustomerUpdateAge") %>>
<br>
    <label for="address1"><b>Address</b></label>
    <input type="text" placeholder="Enter address" name="customerUpdateAdr1" id="caddress1" value = <%= session.getAttribute("CustomerUpdateAdr1") %>>
<br>
    <label for="address2"><b>Address</b></label>
    <input type="text" placeholder="Enter address" name="customerUpdateAdr2" id="caddress2" value = <%= session.getAttribute("CustomerUpdateAdr2") %>>
<br>
       <label for="city"><b>City</b></label>
    <input type="text" placeholder="city" name="customerUpdateCity" id="ccity" value = <%= session.getAttribute("CustomerUpdateCity") %>>
    <br>
  <label for="state "><b>State</b></label>
   <input type="text" placeholder="state" name="customerUpdateState" id="cstate" value = <%= session.getAttribute("CustomerUpdateState") %>>
 <br>
    <button class="button2" type = "submit" name = "regBtns" value = "update">Update</button>
    <button class="button2"  type="reset" class="registerbtn">Reset</button>
  </div>
  
</form>
<form name="Menuform" action="webServlet" method="post">
	<button class="button2"  type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>

</body>
</html>