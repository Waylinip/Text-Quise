package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/owl")
public class OwlServlet extends HttpServlet {
    public final String FIRST_RIDDLE = "I am not alive, but I grow; I do not have lungs, but I need air; I do not have a mouth, but water kills me. What am I?";
    public final String SECOND_RIDDLE = "The more you take, the more you leave behind. What am I?";
    protected final String FIRST_CORRECT_ANSWER = "shadow";
    protected final String SECOND_CORRECT_ANSWER = "footsteps";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean hasSword = (Boolean) request.getSession().getAttribute("hasSword");

        if (hasSword != null && hasSword) {

            request.getSession().setAttribute("message", "The owl saw the sword, took it as a threat and attacked you.");
            response.sendRedirect("/Final-Project/endGame.jsp");
            return;
        }

        String currentRiddle = (String) request.getSession().getAttribute("currentRiddle");
        if (currentRiddle == null) {
            currentRiddle = FIRST_RIDDLE;
            request.getSession().setAttribute("currentRiddle", FIRST_RIDDLE);
        }

        request.getSession().setAttribute("attempts", 0);

        request.getRequestDispatcher("/WEB-INF/Views/owl.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String answer = request.getParameter("answer");
        HttpSession session = request.getSession();

        Boolean hasSword = (Boolean) session.getAttribute("hasSword");
        if (hasSword != null && hasSword) {
            session.setAttribute("message", "The owl saw the sword, took it as a threat and attacked you.");

            response.sendRedirect("/Final-Project/endGame.jsp");
        }

        String currentRiddle = (String) session.getAttribute("currentRiddle");
        Integer attempts = (Integer) session.getAttribute("attempts");
        if (attempts == null) attempts = 0;

        boolean isCorrect = false;
        if (FIRST_RIDDLE.equals(currentRiddle) && FIRST_CORRECT_ANSWER.equalsIgnoreCase(answer)) {
            isCorrect = true;
            session.setAttribute("currentRiddle", SECOND_RIDDLE);
            session.setAttribute("attempts", 0);
        } else if (SECOND_RIDDLE.equals(currentRiddle) && SECOND_CORRECT_ANSWER.equalsIgnoreCase(answer)) {
            isCorrect = true;
            session.setAttribute("hasKey", true);
        }

        if (isCorrect) {
            if (SECOND_RIDDLE.equals(currentRiddle)) {
                response.sendRedirect("/Final-Project/forest");
            } else {
                response.sendRedirect("/Final-Project/owl");
            }
        } else {
            attempts++;
            session.setAttribute("attempts", attempts);

            if (attempts >= 2) {
                request.getSession().setAttribute("message", " I'll never let a fool pass, you'll get knowledge then you'll come.");
                response.sendRedirect("/Final-Project/endGame.jsp");
            } else {
                request.getRequestDispatcher("/WEB-INF/Views/owl.jsp").forward(request, response);
            }
        }
    }
}
