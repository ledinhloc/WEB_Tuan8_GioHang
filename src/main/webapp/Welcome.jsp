<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <title>Chào mừng</title>
</head>
<body>
 	<h2>Chào mừng, <%= session.getAttribute("username") %>!</h2>
    <a href="Logout.jsp">Đăng xuất</a>
</body>
</html>