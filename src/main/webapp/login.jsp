<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    
    <link rel="stylesheet" href="styles2.css">
</head>
<body>
    <h1>Login</h1>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Invalid username or password.</p>
    <% } %>
    <form action="login" method="post"> <br>
        Username: <input type="text" name="username"><br><br><br>
        Password: <input type="password" name="password"><br><br><br>
        <input type="submit" value="Login"><br>
    </form><br>
    <p>Don't have an account? <a href="register.jsp">Register here</a>.</p>
</body>
</html>