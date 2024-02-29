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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            switch (user.getRol().getId()) {
                case 2:
                    response.sendRedirect("admin");
                    break;
                case 1:
                    List<Task> tasks = appController.getTasksByUser(user);
                    request.setAttribute("tasks", tasks);
                    request.getRequestDispatcher("user.jsp").forward(request, response);
                    break;
            }
        } else
            response.sendRedirect("login");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dateString = request.getParameter("deadline");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);

        appController.newTask(title, description,date, user);

        List<Task> tasks = appController.getTasksByUser(user);
        request.setAttribute("tasks", tasks);
        response.sendRedirect("user");
    }
}