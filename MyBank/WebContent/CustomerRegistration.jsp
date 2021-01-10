<!DOCTYPE html>
<html>
<head>

<link rel = "stylesheet" href="web.css"> 

<meta chasrset="ISO-8859-1">
<title>Customer Registration</title>

</head>
<body>

 <div class="container">
 <form name="Customerform" action="home.html"  method="post">
 
    <h1>Registration Page</h1>
    <label for="ssn"><b>Customer SSN</b></label>
    <input id="ssn" type="text" name="ssn" placeholder="123-45-6789"  required pattern="\d{9}" required>
<br>
    <label for="name"><b>Customer Name</b></label>
    <input type="text" placeholder="name" name="customerName"  id="customerName" pattern="[a-zA-Z][a-zA-Z ]{2,}" required >
    
<br>
    <label for="age"><b>Age</b></label>
    <input type="number" id="customerAge" max="99" name="customerAge"  required>
<br>
    <label for="address"><b>Address</b></label>
    <input type="text" placeholder="Enter address" name="addressLine1" id="addressLine1" required>
<br>
       <label for="city"><b>City</b></label>
    <input type="text" placeholder="city" name="city" id="city" required>
    <br>
  <label for="state "><b>State</b></label>
   <input type="text" placeholder="state" name="state" id="state" required>
 <br>
    <button  type="submit" class="registerbtn">Submit</button>
   <button type="reset" class="registerbtn">Reset</button>

  </div>

</form>


</body>
</html>
