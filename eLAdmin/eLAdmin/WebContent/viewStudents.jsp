<%@page import="javax.crypto.SecretKey"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="javascript" href="https://code.jquery.com/jquery-3.3.1.min.js">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>view students</title>

<link rel="stylesheet" type="text/css" href="./css/datatables.css">
<link rel="stylesheet" type="text/css" href="./css/datatables.min.css">
<link rel="stylesheet" type="text/css" href="./css/jquery.dataTables.min.css">
<script type="text/javascript" src="./js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="./js/datatables.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
    $('table.display').DataTable();
} );
</script>
<style>
#button {
     line-height: 12px;
     width: 60px;
     font-size: 8pt;
     font-family: tahoma;
     margin-top: 1px;
     margin-right: 2px;
     position:absolute;
     top:10;
     right:0;
 }
</style>
<script>
<%System.out.println("In logout page");%>

</script>
</head>

<body>
<input type = "button" id = "button" name = "button" value = "Logout"><br><br>
<table id="" class="display" style="width:100%">
<thead>
<tr><th>Student Id</th><th>Name</th><th>Branch</th><th>Email ID</th><th>Phone No.</th><th></th></tr>
</thead>
<tbody>
		<%try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{ 
	
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearn", "root", "root");
			statement=connection.createStatement();
			String sql ="SELECT * FROM students where status = 1";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { 
				String sid = resultSet.getString("studentId");
				String name = resultSet.getString("name");
				String branch = resultSet.getString("branch");
				String mailId = resultSet.getString("mailId");
				String phno = resultSet.getString("phone"); %>
		<tr bgcolor="#F0FFFF">

			<td><%=sid %></td>
			<td><%=name%></td>
			<td><%=branch %></td>
			<td><%=mailId %></td>
			<td><%=phno %></td>
			<td><a href="rejectStudent.jsp?id=<%=sid%>">Cancel Student</a></td>

		</tr>

		<% } 
	      
} catch (Exception e) {
e.printStackTrace();
}
%>
</tbody>
</table>


</body>
</html>