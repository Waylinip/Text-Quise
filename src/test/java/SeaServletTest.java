import controller.ForestServlet;
import controller.SeaServlet;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class SeaServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private SeaServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new SeaServlet();
    }

    @Test
    public void testDoGet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("riddleSolved")).thenReturn(false);
        when(session.getAttribute("chestOpened")).thenReturn(false);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/sea.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("riddleSolved", false);
        verify(request).setAttribute("chestOpened", false);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPostWithStarAction() throws Exception {
        when(request.getParameter("action")).thenReturn("star");
        when(request.getSession()).thenReturn(session);

        servlet.doPost(request, response);

        verify(response).sendRedirect("http://localhost:8080/Final-Project/endGame.jsp");
    }

    @Test
    public void testDoPostWithLightAction() throws Exception {
        when(request.getParameter("action")).thenReturn("light");
        when(request.getSession()).thenReturn(session);

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/sea.jsp")).thenReturn(requestDispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("riddleSolved", true);
        verify(request).getRequestDispatcher("/sea.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostWithOpenChestAction() throws Exception {
        when(request.getParameter("action")).thenReturn("open_chest");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("riddleSolved")).thenReturn(true);

        servlet.doPost(request, response);

        verify(session).setAttribute("chestOpened", true);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(response).sendRedirect(captor.capture());
        String redirectUrl = captor.getValue();
        assertEquals("/magicstone.jsp", redirectUrl);
    }
}


