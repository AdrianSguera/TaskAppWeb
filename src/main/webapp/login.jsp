<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="assets/css/loginStyle.css">
</head>
<body>

<div class="login-container">
    <div class="login-header">
        <h2>Login</h2>
    </div>

    <form action="" method="post">
        <div class="input-group">
            <label for="username">User:</label>
            <input type="text" id="username" name="username" required>
        </div>

        <div class="input-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn-login">Login</button>

    </form>
    <c:if test="${message!=null}">
        <p>${message}</p>
    </c:if>
</div>
</body>
</html>
