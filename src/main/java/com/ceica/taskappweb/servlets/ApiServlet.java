package com.ceica.taskappweb.servlets;

import com.ceica.taskappweb.controller.AppController;
import com.ceica.taskappweb.modelos.Task;
import com.ceica.taskappweb.modelos.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "apiServlet", value = "/api")
public class ApiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        User user = (User) request.getSession().getAttribute("user");

        if (request.getParameter("idTask") != null) {
            deleteTask(request, response, appController, user);
        }

        if (request.getParameter("idUser") != null) {
            deleteUser(request, response, appController);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("api");
    }

    public String getTaskTableHTML(AppController appController) {

        List<Task> taskList = appController.getTasks();

        StringBuilder tablaHTML = new StringBuilder();

        if (!taskList.isEmpty()){
            for (Task task : taskList) {
                tablaHTML.append("<tr>");
                tablaHTML.append("<td>").append(task.getId()).append("</td>");
                tablaHTML.append("<td>").append(task.getTitle()).append("</td>");
                tablaHTML.append("<td>").append(task.getDescription()).append("</td>");
                tablaHTML.append("<td>").append(task.getCreation_time()).append("</td>");
                tablaHTML.append("<td>").append(task.getDeadline()).append("</td>");
                tablaHTML.append("<td>").append(task.getStatus()).append("</td>");
                tablaHTML.append("<td>").append(task.getUser().getUsername()).append("</td>");
                tablaHTML.append("<td><i class=\"fa-solid fa-file-pen\"></i> <i onClick=\"deleteTask(").append(task.getId()).append(")\" class=\"fa-solid fa-trash\"></i></td>");
                tablaHTML.append("</tr>");
            }
        } else
            tablaHTML.append("<tr><td colspan=\"8\">No tasks assigned</td></tr>");

        return tablaHTML.toString();
    }
    private void deleteTask(HttpServletRequest request, HttpServletResponse response, AppController appController, User user) throws IOException {
        int idTask;
        try {
            idTask = Integer.parseInt(request.getParameter("idTask"));
        } catch (NumberFormatException e) {
            // Manejar la excepción si el parámetro idTask no es un entero
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idTask debe ser un entero válido");
            return;
        }

        appController.deleteTaskById(idTask);
        response.getWriter().write(getTaskTableHTML(appController));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response, AppController appController) throws IOException {
        int idUser;
        try {
            idUser = Integer.parseInt(request.getParameter("idUser"));
        } catch (NumberFormatException e) {
            // Manejar la excepción si el parámetro idUser no es un entero
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idUser debe ser un entero válido");
            return;
        }

        appController.deleteUserById(idUser);
        List<User> userList = appController.getUsers();
        Gson gson = new Gson();
        String userListJson = gson.toJson(userList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userListJson);
    }

}