package com.ceica.taskappweb.servlets;

import com.ceica.taskappweb.controller.AppController;
import com.ceica.taskappweb.modelos.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AppController appController = new AppController();
        HttpSession session = request.getSession();
        session.setAttribute("user", appController.getUserLogged());
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (appController.login(username, password)) {
            session.setAttribute("user", appController.getUserLogged());
            if (appController.isAdmin())
                response.sendRedirect("admin");
            else
                response.sendRedirect("user");
        } else {
            request.setAttribute("message", "Incorrect user or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
