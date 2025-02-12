<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Profile</title>
<link rel="stylesheet" href="styles2.css">
</head>
<body>
    <h1>Update Profile</h1>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Update failed. Please try again.</p>
    <% } %>
    <% if (request.getParameter("success") != null) { %>
        <p style="color: green;">Profile updated successfully!</p>
    <% } %>
    <form action="updateProfile" method="post"><br>
        New Username: <input type="text" name="username" required><br><br><br>
        New Password: <input type="password" name="password" required><br><br><br>
        <input type="submit" value="Update Profile"><br>
    </form><br>
    <a href="gallery" class="a1">Back to Gallery</a>
</body>
</html>