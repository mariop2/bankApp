<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@page import="java.sql.*" %>
<%@page import="java.io.PrintWriter" %>

<%  session.setAttribute("value", "displayCustomer"); %>


</head>
<body>
	<h1>Table</h1>
	<%
    try
    {
        PrintWriter pw=response.getWriter();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abcd");
		
		pw.print(con);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("SELECT * FROM tbl_customer;");
		pw.print("hello");

	%>
	 <form name="displayCustomer" action="webServlet" method="post">
	<table border=1 style="text-align:center">
          <tr>
             <th>ID</th>
             <th>NAME</th>
             <th>AGE</th>
             <th>ADR1</th>
             <th>ADR2</th>
             <th>CITY</th>
             <th>STATE</th>
      <tbody>
        <%
		pw.print(rs.next());
        while(rs.next())
        {
        %>
            <tr>
                <td><%=rs.getString("customerId") %></td>
                <td><%=rs.getString("customerName") %></td>
                <td><%=rs.getString("customerAge") %></td>
                <td><%=rs.getString("customerAddress1") %></td>
                <td><%=rs.getString("customerAddress2") %></td>
                <td><%=rs.getString("customerCity") %></td>
                <td><%=rs.getString("customerState") %></td>      
            </tr>
            <%}%>
           </tbody>
        </table><br>
    <%}
    catch(Exception e){ %> <br>
    <%
    }
    
    finally{
        //st.close();
        //con.close();
    }
    %>
    </form>
</body>
</html>