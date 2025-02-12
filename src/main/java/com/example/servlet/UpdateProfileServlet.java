package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = (User) session.getAttribute("user");
        user.setUsername(username);
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        try {
            boolean isUpdated = userDAO.updateUser(user);
            if (isUpdated) {
                session.setAttribute("user", user); // Update session with new user details
                response.sendRedirect("updateProfile.jsp?success=1");
            } else {
                response.sendRedirect("updateProfile.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("updateProfile.jsp?error=1");
        }
    }
}