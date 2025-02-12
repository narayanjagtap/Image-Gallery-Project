package com.example.servlet;

import com.example.dao.ImageDAO;
import com.example.model.Image;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet("/gallery")
public class ImageDisplayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int page = 1;
        int limit = 6; // Number of images per page
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page - 1) * limit;

        ImageDAO imageDAO = new ImageDAO();
        List<Image> images = imageDAO.selectImagesWithPagination(limit, offset);
        int totalImages = imageDAO.getTotalImages();
        int totalPages = (int) Math.ceil((double) totalImages / limit);

        request.setAttribute("images", images);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("gallery.jsp").forward(request, response);
    }
}