package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/sea")
public class SeaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean riddleSolved = (Boolean) session.getAttribute("riddleSolved");
        Boolean chestOpened = (Boolean) session.getAttribute("chestOpened");

        if (riddleSolved == null) {
            riddleSolved = false;
        }
        if (chestOpened == null) {
            chestOpened = false;
        }

        request.setAttribute("riddleSolved", riddleSolved);
        request.setAttribute("chestOpened", chestOpened);

        request.getRequestDispatcher("/sea.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        Boolean riddleSolved = (Boolean) session.getAttribute("riddleSolved");
        Boolean chestOpened = (Boolean) session.getAttribute("chestOpened");

        if (riddleSolved == null) {
            riddleSolved = false;
        }
        if (chestOpened == null) {
            chestOpened = false;
        }

        if ("star".equals(action) || "shadow".equals(action)) {

            request.getSession().setAttribute("message", "Wrong answer, the chest is closed to you forever.");
            response.sendRedirect("http://localhost:8080/Final-Project/endGame.jsp");

        } else if ("light".equals(action)) {
            riddleSolved = true;
            session.setAttribute("riddleSolved", riddleSolved);
            request.setAttribute("riddleSolved", riddleSolved);
            request.getRequestDispatcher("/sea.jsp").forward(request, response);

        } else if ("open_chest".equals(action)) {
            if (riddleSolved) {
                chestOpened = true;
                session.setAttribute("chestOpened", chestOpened);

                String contextPath = request.getContextPath() != null ? request.getContextPath() : "";
                response.sendRedirect(contextPath + "/magicstone.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/sea");
            }
        } else if ("share_gold".equals(action)) {
            request.setAttribute("result", "shared");
            request.getRequestDispatcher("/sea.jsp").forward(request, response);

        } else if ("keep_gold".equals(action)) {
            request.setAttribute("result", "kept");
            request.getRequestDispatcher("/sea.jsp").forward(request, response);

        }
    }
}






