package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


    @WebServlet("/reset")
    public class GameServlet extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            request.getSession().invalidate();

            response.sendRedirect(request.getContextPath() + "/start");
        }

    }