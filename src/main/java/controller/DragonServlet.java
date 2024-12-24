package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/dragon")
public class DragonServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean snakeAwakened = (Boolean) request.getSession().getAttribute("snakeAwakened");
        String gameState = (String) request.getSession().getAttribute("gameState");
        Boolean hasSword = (Boolean) request.getSession().getAttribute("hasSword"); // Проверка наличия меча

        request.setAttribute("snakeAwakened", snakeAwakened);
        request.setAttribute("gameState", gameState);
        request.setAttribute("hasSword", hasSword); // Передаем информацию о мечах

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String playerAnswer = request.getParameter("answer");

        Boolean hasSword = (Boolean) request.getSession().getAttribute("hasSword");

        if ("wake_up".equals(action)) {

            request.getSession().setAttribute("snakeAwakened", true);
        } else if ("sneak_past".equals(action)) {
            request.getSession().setAttribute("message", "You try to sneak past the dragon, but it wakes up and devours you!");
            response.sendRedirect(request.getContextPath() + "/endGame.jsp");
            return;
        }else if ("fight".equals(action)) {
            if (hasSword == null || !hasSword) {
                request.getSession().setAttribute("message", "You try to fight the dragon without a sword, but it devours you!");
                response.sendRedirect(request.getContextPath() + "/endGame.jsp");
                return;
            } else {
                request.getSession().setAttribute("hasSword", false);
                request.setAttribute("message", "You fight the dragon bravely and defeat it, but lose your sword in the process.");
                request.setAttribute("showSeaButton", true);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp");
                dispatcher.forward(request, response);
                return;
            }

        } else if ("submit_answer".equals(action) && playerAnswer != null) {
            if ("map".equalsIgnoreCase(playerAnswer)) {
                request.getSession().setAttribute("gameState", "success");
                request.setAttribute("message", "The dragon is pleased with your answer and lets you pass to the sea!");
            } else {
                request.getSession().setAttribute("gameState", "toll");
                request.setAttribute("message", "Wrong answer! The dragon won't let you go. Its three heads snarl in unison, and the ground trembles as it prepares to strike. You have failed, adventurer, and your fate is sealed. The dragon opens its jaws, and you know that your journey ends here...\n"
                        + "You have only one choice left: fight the dragon to the death!");
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp");
        dispatcher.forward(request, response);
    }
}



