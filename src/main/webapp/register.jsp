<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegisterPage</title>
    <link rel="stylesheet" href="styles2.css">
</head>
<body>
    <h1>Register</h1>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Registration failed. Please try again.</p>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
        <p style="color: green;">Registration successful! Please login.</p>
    <% } %>
    <form action="register" method="post"><br>
        Username: <input type="text" name="username" required><br><br><br>
        Password: <input type="password" name="password" required><br><br><br>
        <input type="submit" value="Register"><br>
    </form><br>
    <p>Already have an account? <a href="login.jsp">Login here</a>.</p><br>
</body>
</html>