import controller.DragonServlet;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import static org.mockito.Mockito.*;

class DragonServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    public DragonServletTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGetAttributesSetCorrectly() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("snakeAwakened")).thenReturn(true);
        when(session.getAttribute("gameState")).thenReturn("ongoing");
        when(session.getAttribute("hasSword")).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("snakeAwakened", true);
        verify(request).setAttribute("gameState", "ongoing");
        verify(request).setAttribute("hasSword", false);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostWakeUpAction() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("wake_up");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("snakeAwakened", true);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostSneakPastAction() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("sneak_past");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/game");

        servlet.doPost(request, response);

        verify(session).setAttribute("message", "You try to sneak past the dragon, but it wakes up and devours you!");
        verify(response).sendRedirect("/game/endGame.jsp");
    }

    @Test
    void testDoPostFightWithoutSword() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("fight");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("hasSword")).thenReturn(false);
        when(request.getContextPath()).thenReturn("/game");

        servlet.doPost(request, response);

        verify(session).setAttribute("message", "You try to fight the dragon without a sword, but it devours you!");
        verify(response).sendRedirect("/game/endGame.jsp");
    }

    @Test
    void testDoPostFightWithSword() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("fight");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("hasSword")).thenReturn(true);
        when(request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("hasSword", false);
        verify(request).setAttribute("message", "You fight the dragon bravely and defeat it, but lose your sword in the process.");
        verify(request).setAttribute("showSeaButton", true);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostSubmitCorrectAnswer() throws Exception {

        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("submit_answer");
        when(request.getParameter("answer")).thenReturn("map");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("gameState", "success");
        verify(request).setAttribute("message", "The dragon is pleased with your answer and lets you pass to the sea!");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostSubmitWrongAnswer() throws Exception {
        DragonServlet servlet = new DragonServlet();

        when(request.getParameter("action")).thenReturn("submit_answer");
        when(request.getParameter("answer")).thenReturn("wrong");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/Views/dragon.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("gameState", "toll");
        verify(request).setAttribute(eq("message"), contains("Wrong answer! The dragon won't let you go."));
        verify(dispatcher).forward(request, response);
    }
}

