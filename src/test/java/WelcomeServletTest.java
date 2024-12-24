import controller.WelcomeServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.ArgumentCaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class WelcomeServletTest {

    private WelcomeServlet welcomeServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        welcomeServlet = new WelcomeServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        Map<String, Object> sessionAttributes = new HashMap<>();
        when(session.getAttribute(anyString())).thenAnswer(invocation -> sessionAttributes.get(invocation.getArgument(0)));
        doAnswer(invocation -> {
            sessionAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
            return null;
        }).when(session).setAttribute(anyString(), any());
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("startGame.jsp")).thenReturn(requestDispatcher);

        welcomeServlet.doGet(request, response);

        verify(request).getRequestDispatcher("startGame.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_ValidPlayerName() throws ServletException, IOException {
        String playerName = "Player1";
        when(request.getParameter("playerName")).thenReturn(playerName);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/forest")).thenReturn(requestDispatcher);

        welcomeServlet.doPost(request, response);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(session).setAttribute(eq("playerName"), captor.capture());

        assertEquals("Player1", captor.getValue());

        verify(request).getRequestDispatcher("/forest");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_EmptyPlayerName() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/forest")).thenReturn(requestDispatcher);

        welcomeServlet.doPost(request, response);

        verify(session, never()).setAttribute(eq("playerName"), any());

        verify(request).getRequestDispatcher("/forest");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_NullPlayerName() throws ServletException, IOException {

        when(request.getParameter("playerName")).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/forest")).thenReturn(requestDispatcher);

        welcomeServlet.doPost(request, response);

        verify(session, never()).setAttribute(eq("playerName"), any());

        verify(request).getRequestDispatcher("/forest");
        verify(requestDispatcher).forward(request, response);
    }
}



