<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="assets/css/userStyle.css">
    <script src="assets/js/user.js"></script>
</head>
<body>
<header>
    <img src="assets/images/logo.png" alt="Logo">
    <h1>AppWeb</h1>
    <div class="separator"></div>
    <a class="close-session-link" href="login">Close Session</a>
</header>
<div class="options-panel">
    <button id="crear-btn" class="option-btn">New Task</button>
    <button id="ver-btn" class="option-btn">All Tasks</button>
</div>

<div id="crear-section" class="option-section">
    <h1>New task</h1>
    <form id="crear-tarea-form" action="user" method="post">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title"><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br>

        <label for="deadline">Deadline:</label>
        <input type="datetime-local" id="deadline" name="deadline"><br>

        <button type="submit">Submit</button>
    </form>
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