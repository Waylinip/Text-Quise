import controller.OwlServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.mockito.Mockito.*;


public class OwlServletTest {

    private OwlServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new OwlServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet_PlayerHasSword() throws ServletException, IOException {
        when(session.getAttribute("hasSword")).thenReturn(true);

        servlet.doGet(request, response);

        verify(session).setAttribute("message", "The owl saw the sword, took it as a threat and attacked you.");
        verify(response).sendRedirect("/Final-Project/endGame.jsp");
    }

    @Test
    void testDoGet_FirstRiddle() throws ServletException, IOException {
        when(session.getAttribute("hasSword")).thenReturn(false);
        when(session.getAttribute("currentRiddle")).thenReturn(null);
        when(request.getRequestDispatcher("/WEB-INF/Views/owl.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(session).setAttribute("currentRiddle", servlet.FIRST_RIDDLE);
        verify(session).setAttribute("attempts", 0);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_FirstRiddleCorrectAnswer() throws ServletException, IOException {
        when(session.getAttribute("currentRiddle")).thenReturn(servlet.FIRST_RIDDLE);
        when(request.getParameter("answer")).thenReturn("shadow");

        servlet.doPost(request, response);

        verify(session).setAttribute("currentRiddle", servlet.SECOND_RIDDLE);
        verify(session).setAttribute("attempts", 0);
        verify(response).sendRedirect("/Final-Project/owl");
    }

    @Test
    void testDoPost_FirstRiddleIncorrectAnswer() throws ServletException, IOException {
        when(session.getAttribute("currentRiddle")).thenReturn(servlet.FIRST_RIDDLE);
        when(session.getAttribute("attempts")).thenReturn(0);
        when(request.getParameter("answer")).thenReturn("wrongAnswer");
        when(request.getRequestDispatcher("/WEB-INF/Views/owl.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("attempts", 1);  // Без eq
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_FailureAfterTwoAttempts() throws ServletException, IOException {
        when(session.getAttribute("currentRiddle")).thenReturn(servlet.FIRST_RIDDLE);
        when(session.getAttribute("attempts")).thenReturn(2);
        when(request.getParameter("answer")).thenReturn("wrongAnswer");

        servlet.doPost(request, response);

        // Убедитесь, что пробелы в строках совпадают.
        verify(session).setAttribute("message", " I'll never let a fool pass, you'll get knowledge then you'll come.");
        verify(response).sendRedirect("/Final-Project/endGame.jsp");
    }

    @Test
    void testDoPost_SecondRiddleCorrectAnswer() throws ServletException, IOException {
        when(session.getAttribute("currentRiddle")).thenReturn(servlet.SECOND_RIDDLE);
        when(request.getParameter("answer")).thenReturn("footsteps");

        servlet.doPost(request, response);

        verify(session).setAttribute("hasKey", true);
        verify(response).sendRedirect("/Final-Project/forest");
    }

    @Test
    void testDoPost_SecondRiddleIncorrectAnswer() throws ServletException, IOException {
        when(session.getAttribute("currentRiddle")).thenReturn(servlet.SECOND_RIDDLE);
        when(request.getParameter("answer")).thenReturn("wrongAnswer");
        when(request.getRequestDispatcher("/WEB-INF/Views/owl.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("attempts", 1);  // Без eq
        verify(dispatcher).forward(request, response);
    }
}




