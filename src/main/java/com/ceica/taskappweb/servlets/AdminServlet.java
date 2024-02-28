package com.ceica.taskappweb.servlets;

import com.ceica.taskappweb.modelos.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        switch (user.getRol().getId()) {
            case 1:
                response.sendRedirect("user");
                break;
            case 2:
                request.getRequestDispatcher("admin.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
