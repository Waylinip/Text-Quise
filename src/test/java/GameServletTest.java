import controller.GameServlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

import static org.mockito.Mockito.*;
class GameServletTest {

    private GameServlet gameServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        gameServlet = new GameServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet() throws IOException {
        gameServlet.doGet(request, response);

        verify(session, times(1)).invalidate();

        String expectedRedirect = request.getContextPath() + "/start";
        verify(response).sendRedirect(expectedRedirect);
    }
}

