<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" 
href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
crossorigin="anonymous">
</head>
<body>
<div align="center" class="container">
<h1>Register Here</h1>
<form action="save" method="post">
  
   <div> Id      :<input type="text" name="id" class="form-control" placeholder="Enter Id"></div><br>
   <div> Email   :<input type="text" name="email" class="form-control" placeholder="Enter Email Id"></div><br>
   <div> Address :<input type="text" name="address" class="form-control" placeholder="Enter Address"></div><br>
    <input type="submit" value="save" class="btn btn-success">
  
</form>
</div>
</body>
</html>