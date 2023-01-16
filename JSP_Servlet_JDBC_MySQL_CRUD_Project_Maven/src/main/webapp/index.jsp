<%@page import="com.dao.StudentDAO"%>
<%@page import="com.entity.Student"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.conn.DBConnect"%>
<%@page import= "java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="css-js.jsp" %>
</head>
<body class="bg-light">
<%@include file="navbar.jsp" %>

<%-- <%
  Connection conn=DBConnect.getConnection();
  out.println(conn);
%> --%>

<div class="container p-3">
<div class="card">
  <div class="card-body">
 
 <p class="text-center mt-3 fs-1">All Students Details</p>
 
 <!-- Success and Error Message Printing -->
  <c:if test="${not empty success }">
    <p class="text-center text-success">${success }</p>
    <c:remove var="success"></c:remove>
  </c:if>
  <c:if test="${not empty error }">
    <p class="text-center text-danger">${error }</p>
    <c:remove var="error"></c:remove>
  </c:if>
 
  <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Full Name</th>
      <th scope="col">DOB</th>
      <th scope="col">Address</th>
      <th scope="col">Qualification</th>
      <th scope="col">Email</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  
  <%
   StudentDAO stDao=new StudentDAO(DBConnect.getConnection());
   List<Student> list=stDao.getAllStudent();
   for(Student std:list)
   {
  %> 
	   <tr>
	      <th scope="row"><%=std.getName() %></th>
	      <td><%=std.getDob() %></td>
	      <td><%=std.getAddress() %></td>
	      <td><%=std.getQualification() %></td>
	      <td><%=std.getEmail() %></td>
	      <td>
	        <a href="edit-student.jsp?id=<%=std.getId() %>" class="btn btn-sm btn-primary">edit</a>
	        <a href="delete?id=<%=std.getId() %>" class="btn btn-sm btn-danger">delete</a>
	      </td>
	    </tr>
   <% }%>
 
  
    
  </tbody>
</table>
 </div>
  </div>
</div>
</body>
</html>