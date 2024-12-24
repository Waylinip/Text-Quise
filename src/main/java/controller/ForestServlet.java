package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;






@WebServlet("/forest")
public class ForestServlet extends HttpServlet {
    private boolean explored = false;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String playerName = (String) session.getAttribute("playerName");
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Adventurer";
            session.setAttribute("playerName", playerName);
        }

        boolean hasKey = getSessionAttribute(session, "hasKey");
        boolean hasSword = getSessionAttribute(session, "hasSword");
        boolean explored = getSessionAttribute(session, "explored");

        request.setAttribute("playerName", playerName);
        request.setAttribute("hasKey", hasKey);
        request.setAttribute("hasSword", hasSword);
        request.setAttribute("explored", explored);

        String action = request.getParameter("action");
        if ("talk_to_owl".equals(action)) {
            if (hasSword) {
                request.setAttribute("message", "The owl sees your sword and perceives it as a threat! The owl attacks and kills you.");
            } else {
                request.setAttribute("message", "The owl greets you and shares its wisdom.");
            }
            request.getRequestDispatcher("/WEB-INF/Views/owl.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/Views/forest.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("explore".equals(action)) {
            session.setAttribute("explored", true);
        } else if ("go_to_cave".equals(action)) {
            boolean hasKey = getSessionAttribute(session, "hasKey");
            if (hasKey) {
                response.sendRedirect("/Final-Project/cave");
                return;
            } else {
                request.setAttribute("errorMessage", "You need a key to enter the cave!");
                request.getRequestDispatcher("/WEB-INF/Views/forest.jsp").forward(request, response);
                return;
            }
        } else if ("take_sword".equals(action)) {
            session.setAttribute("hasSword", true);
        }else if ("talk_to_owl".equals(action)) {

            response.sendRedirect("/Final-Project/owl");
            return;
        }
            response.sendRedirect("/Final-Project/forest");
    }

    private boolean getSessionAttribute(HttpSession session, String attributeName) {
        Boolean value = (Boolean) session.getAttribute(attributeName);
        return (value != null) ? value : false;
    }
}




