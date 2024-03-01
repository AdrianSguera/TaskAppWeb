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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="tableTask">
        <c:choose>
            <c:when test="${not empty tasks}">
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td>${task.getId()}</td>
                        <td>${task.getTitle()}</td>
                        <td>${task.getDescription()}</td>
                        <td>${task.getCreation_time()}</td>
                        <td>${task.getDeadline()}</td>
                        <td>${task.getStatus()}</td>
                        <td>${task.getUser().getUsername()}</td>
                        <td>
                            <i class="fa-solid fa-file-pen"></i>
                            <i onClick="deleteTask(${task.getId()})" class="fa-solid fa-trash"></i>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8">No tasks assigned</td>
                </tr>
            </c:otherwise>
        </c:choose>

        </tbody>
    </table>
</div>
<script rel="stylesheet" src="assets/js/user.js"></script>
</body>
</html>