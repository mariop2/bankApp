<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style>

body {font-family: Arial, Helvetica, sans-serif;}


button {
  /* background-color: #4CAF50; */
  background-color:rgb(43, 92, 124);
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 80%;
}

button:hover {
  opacity: 0.8;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container { 
  padding: 16px;
  height: 200px;
  position: relative;
  /* border: 3px solid green; */
}

.center {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

span.psw {
  float: right;
  padding-top: 16px;
}


</style>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Bank</title>
</head>
<body>  
    <div class="imgcontainer">
	    <img src="images/login.png" alt="Avatar" class="avatar">
	  </div>
	<div class="container">
	<div class="center">
	<form action="executiveLogin.jsp" method="post">
	    <button type="submit">Customer-Executive Login</button>
	</form>

	<form action="tellerLogin.jsp" method="post">
	    <button type="submit">Teller Login</button>
	</form>
	</div>
	</div>
    
</body>
</html>