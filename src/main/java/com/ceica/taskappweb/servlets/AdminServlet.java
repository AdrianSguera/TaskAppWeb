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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        User user = (User) request.getSession().getAttribute("user");
        if (user != null){
            switch (user.getRol().getId()) {
                case 1:
                    response.sendRedirect("user");
                    break;
                case 2:
                    List<Task> tasks = appController.getTasks();
                    request.setAttribute("tasks", tasks);
                    List<User> users = appController.getUsers();
                    request.setAttribute("users", users);
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                    break;
            }
        } else
            response.sendRedirect("login");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int idrol = Integer.parseInt(request.getParameter("idrol"));

        appController.newUser(username,password,idrol);

        List<User> users = appController.getUsers();
        request.setAttribute("users", users);
        response.sendRedirect("user");
    }
}
