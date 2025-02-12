<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Image</title>
<link rel="stylesheet" href="styles2.css">
</head>
<body>
	<h1>Upload Image</h1> 
    <form action="upload" method="post" enctype="multipart/form-data"><br><br>
        Name: <input type="text" name="name"><br><br><br>
		Description: <textarea name="description" style="width: 370px; height: 100px; padding: 10px; font-size: 16px; resize: vertical;"></textarea><br><br><br>
        Image: <input type="file" name="image"><br><br><br>
        <input type="submit" value="Upload"><br>
    </form>
</body>
</html>