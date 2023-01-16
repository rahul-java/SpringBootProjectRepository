<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
</head>
<body>
${students}
<hr>
<table border="2">
<tr>
<th>Student_Id</th><th>Student_Name</th><th>Course</th><th>Fees</th>
</tr>
<c:forEach var="student" items="${students}">
<tr>
<td align="center">${student.id}</td><td align="center">${student.name}</td><td align="center">${student.course}</td><td align="center">${student.fee}</td>
</tr>
</c:forEach>
</table>
</body>
</html>