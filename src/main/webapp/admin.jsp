<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="assets/css/adminStyle.css">
    <script src="assets/js/admin.js"></script>
</head>
<body>
<header>
    <img src="assets/images/logo.png" alt="Logo">
    <h1>AppWeb</h1>
    <div class="separator"></div>
    <a class="close-session-link" href="login">Close Session</a>
</header>
<div class="options-panel">
    <button id="crear-btn" class="option-btn">New user</button>
    <button id="modificar-btn" class="option-btn">All users</button>
    <button id="eliminar-btn" class="option-btn">Delete user</button>
    <button id="ver-btn" class="option-btn">All tasks</button>
</div>

<div id="crear-section" class="option-section">
    <h1>New user</h1>
    <form id="crear-tarea-form" action="admin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>

        <label for="idrol">Rol:</label>
        <input type="number" id="idrol" name="idrol"><br>

        <button type="submit">Submit</button>
    </form>
</div>

<div id="modificar-section" class="option-section">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Password</th>
            <th>Rol</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getRol().getId()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="eliminar-section" class="option-section">
    <h1>Eliminar</h1>
    <!-- Contenido para eliminar tarea -->
</div>

<div id="ver-section" class="option-section">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Creation time</th>
            <th>Deadline</th>
            <th>Status</th>
            <th>User</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.getId()}</td>
                <td>${task.getTitle()}</td>
                <td>${task.getDescription()}</td>
                <td>${task.getCreation_time()}</td>
                <td>${task.getDeadline()}</td>
                <td>${task.getStatus()}</td>
                <td>${task.getUser().getUsername()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

