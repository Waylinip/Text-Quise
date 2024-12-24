package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "WelcomeServlet", urlPatterns = "/")
public class WelcomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("startGame.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        System.out.println("Before saving: " + session.getAttribute("playerName"));

        String playerName = request.getParameter("playerName");

        if (playerName != null && !playerName.trim().isEmpty()) {
            session.setAttribute("playerName", playerName);
            System.out.println("Player name saved: " + playerName);
        } else {
            System.out.println("Player name was empty or null.");
        }

        System.out.println("After saving: " + session.getAttribute("playerName"));

        request.getRequestDispatcher("/forest").forward(request, response);
    }

}