package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;




@WebServlet("/cave")
public class CaveServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean passedTrap = (Boolean) request.getSession().getAttribute("passedTrap");
        if (passedTrap != null && passedTrap) {
            response.sendRedirect("/Final-Project/dragon"); // Переход к дракону
        } else {
            request.getRequestDispatcher("/WEB-INF/Views/cave.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("search_torch".equals(action)) {
            request.getSession().setAttribute("hasTorch", true);
            response.sendRedirect("/Final-Project/cave");
        } else if ("go_into_cave".equals(action)) {
            request.getSession().setAttribute("message", "You entered the dark cave without a torch and fell into a trap. You died.");
            response.sendRedirect(request.getContextPath() + "/endGame.jsp");
        } else if ("take_torch".equals(action)) {
            request.getSession().setAttribute("holdingTorch", true);
            response.sendRedirect("/Final-Project/cave");
        } else if ("proceed_to_trap".equals(action)) {
            if (request.getSession().getAttribute("holdingTorch") != null) {
                request.getSession().setAttribute("passedTrap", true);
            }
            response.sendRedirect("/Final-Project/cave");
        } else if ("throw_torch".equals(action)) {
            response.getWriter().println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Game Over</title>
                </head>
                <body>
                    <h1>The torch didn't light up! You fell into a trap and died.</h1>
                    <p>Game Over. You should have held the torch instead of throwing it.</p>
                    <a href="/Final-Project/endGame.jsp">Restart the game</a>
                </body>
                </html>
            """);
        }
    }
}
