import controller.ForestServlet;
import controller.MagicStoneServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

import static org.mockito.Mockito.*;

class MagicStoneServletTest {

    private MagicStoneServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new MagicStoneServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testShareStone() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("share_stone");
        when(request.getRequestDispatcher("/final.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("finalMessage"), contains("Your Kindness United the Worlds"));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testKeepStone() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("keep_stone");
        when(request.getRequestDispatcher("/final.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("finalMessage"), contains("You chose to keep the magical stone, tempted by its immense power."));
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDie() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("die");

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("message"), contains("You tried to wield the magical stone without understanding its true power."));
        verify(response).sendRedirect(contains("/endGame.jsp"));
    }

    @Test
    void testInvalidAction() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("invalid_action");
        when(request.getRequestDispatcher("/final.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("finalMessage"), eq(""));
        verify(dispatcher).forward(request, response);
    }
}

