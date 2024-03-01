package com.ceica.taskappweb.servlets;

import com.ceica.taskappweb.controller.AppController;
import com.ceica.taskappweb.modelos.Task;
import com.ceica.taskappweb.modelos.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "apiServlet", value = "/api")
public class ApiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();

        int idTask = Integer.parseInt(request.getParameter("idTask"));
        appController.deleteTaskById(idTask);

        User user = (User) request.getSession().getAttribute("user");

        response.getWriter().write(getTaskTableHTML(appController, user));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("api");
    }

    public String getTaskTableHTML(AppController appController, User user) {

        List<Task> taskList = appController.getTasksByUser(user);

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
            tablaHTML.append("<tr><td>No tasks assigned</td></tr>");

        return tablaHTML.toString();
    }

}