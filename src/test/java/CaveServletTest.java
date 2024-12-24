import controller.CaveServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

import static org.mockito.Mockito.*;

class CaveServletTest {

    private CaveServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new CaveServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet_PassedTrapTrue() throws ServletException, IOException {
        when(session.getAttribute("passedTrap")).thenReturn(true);

        servlet.doGet(request, response);

        verify(response).sendRedirect("/Final-Project/dragon");
    }

    @Test
    void testDoGet_PassedTrapFalse() throws ServletException, IOException {
        when(session.getAttribute("passedTrap")).thenReturn(null);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/Views/cave.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_SearchTorch() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("search_torch");

        servlet.doPost(request, response);

        verify(session).setAttribute("hasTorch", true);
        verify(response).sendRedirect("/Final-Project/cave");
    }

    @Test
    void testDoPost_GoIntoCave() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("go_into_cave");
        when(request.getContextPath()).thenReturn("/Final-Project");

        servlet.doPost(request, response);

        verify(session).setAttribute("message", "You entered the dark cave without a torch and fell into a trap. You died.");
        verify(response).sendRedirect("/Final-Project/endGame.jsp");
    }

    @Test
    void testDoPost_TakeTorch() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("take_torch");

        servlet.doPost(request, response);

        verify(session).setAttribute("holdingTorch", true);
        verify(response).sendRedirect("/Final-Project/cave");
    }

    @Test
    void testDoPost_ProceedToTrapWithTorch() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("proceed_to_trap");
        when(session.getAttribute("holdingTorch")).thenReturn(true);

        servlet.doPost(request, response);

        verify(session).setAttribute("passedTrap", true);
        verify(response).sendRedirect("/Final-Project/cave");
    }

    @Test
    void testDoPost_ThrowTorch() throws Exception {

        when(request.getParameter("action")).thenReturn("throw_torch");


        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(request).getParameter("action");

        verify(response).getWriter();

        verify(writer).println(contains("The torch didn't light up! You fell into a trap and died."));
        verify(writer).println(contains("<title>Game Over</title>"));
    }
}

