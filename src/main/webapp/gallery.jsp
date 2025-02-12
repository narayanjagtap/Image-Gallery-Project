<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.model.Image" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Image Gallery</title>
    <style>
        body {
        
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
            font-size: 40px;
            font-weight: bold;
            padding: 10px;
            background-color: #6598f7;
            color: white;
        }

        .container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            font-weight: bold;
        }

        .gallery {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(500px, 1fr));
            gap: 20px;
            padding: 20px;
            max-width: 1200px;
            margin: auto;
        }

        .image-box {
            background: white;
            padding: 15px;
            border-radius: 10px;
            text-align: center;
        }

        .image-box img {
            width: 100%;
            height: auto;
            border-radius: 2px;
        }

        .image-box h3 {
            margin: 10px 0;
            font-size: 18px;
            color: #333;
        }

        .image-box p {
            font-size: 14px;
            color: #555;
        }

        .delete-btn {
            display: inline-block;
            padding: 8px 12px;
            background-color: red;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: background 0.3s ease;
            margin-top: 30px;
        }

        .delete-btn:hover {
            background-color: darkred;
        }

        .pagination {
            margin-top: 20px;
        }

        .pagination a {
            padding: 10px 15px;
            margin: 5px;
            text-decoration: none;
            color: white;
            background-color: #6598f7;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        .pagination a:hover {
            background-color: #3366cc;
        }
        
        .div1{
        	text-align: left;
        }
    </style>
</head>
<body>

    <h1>Image Gallery</h1>
    
    <div class="container">
        <a href="upload.jsp" class="delete-btn" style="background-color: #6598f7;">Upload Image</a> &nbsp;&nbsp;
        <a href="updateProfile.jsp" class="delete-btn" style="background-color: #6598f7;">Update Profile</a>	&nbsp;&nbsp;
        <a href="logout" class="delete-btn" style="background-color: #6598f7;">Logout</a>
    </div>

    <div class="gallery">
        <% List<Image> images = (List<Image>) request.getAttribute("images");
           if (images != null) {
               for (Image image : images) { %>
                <div class="image-box">
                    <h3><%= image.getName() %></h3>
                    <p><%= image.getDescription() %></p>
                    <img src="<%= image.getFilePath() %>" alt="<%= image.getName() %>">
                    <br>
                    <a href="delete?id=<%= image.getId() %>" class="delete-btn">Delete</a>
                </div>
        <%   } 
           } else { %>
           <p>No images available.</p>
        <% } %>
    </div>

    <div class="pagination">
        <% int totalPages = (int) request.getAttribute("totalPages");
           int currentPage = (int) request.getAttribute("currentPage");
           for (int i = 1; i <= totalPages; i++) { %>
            <a href="gallery?page=<%= i %>"><%= i %></a>
        <% } %>
    </div>

</body>
</html>
