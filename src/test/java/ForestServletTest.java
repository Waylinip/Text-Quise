
import controller.ForestServlet;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import static org.mockito.Mockito.*;



class ForestServletTest {

    private ForestServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new ForestServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGetWithoutPlayerName() throws Exception {
        System.out.println("Running test: testDoGetWithoutPlayerName");

        when(session.getAttribute("playerName")).thenReturn(null);
        when(request.getRequestDispatcher("/WEB-INF/Views/forest.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(session).setAttribute("playerName", "Adventurer");

        verify(dispatcher).forward(request, response);

        System.out.println("Passed: testDoGetWithoutPlayerName");
    }

    @Test
    void testDoGetWithPlayerName() throws Exception {
        System.out.println("Running test: testDoGetWithPlayerName");

        when(session.getAttribute("playerName")).thenReturn("TestPlayer");
        when(request.getRequestDispatcher("/WEB-INF/Views/forest.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute("playerName", "TestPlayer");

        verify(dispatcher).forward(request, response);

        System.out.println("Passed: testDoGetWithPlayerName");
    }

    @Test
    void testDoPostTakeSword() throws Exception {
        System.out.println("Running test: testDoPostTakeSword");

        when(request.getParameter("action")).thenReturn("take_sword");

        servlet.doPost(request, response);

        verify(session).setAttribute("hasSword", true);

        verify(response).sendRedirect("/Final-Project/forest");

        System.out.println("Passed: testDoPostTakeSword");
    }

    @Test
    void testDoPostGoToCaveWithoutKey() throws Exception {
        System.out.println("Running test: testDoPostGoToCaveWithoutKey");

        when(request.getParameter("action")).thenReturn("go_to_cave");
        when(session.getAttribute("hasKey")).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/Views/forest.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("errorMessage"), eq("You need a key to enter the cave!"));

        verify(dispatcher).forward(request, response);

        System.out.println("Passed: testDoPostGoToCaveWithoutKey");
    }

    @Test
    void testDoPostGoToCaveWithKey() throws Exception {
        System.out.println("Running test: testDoPostGoToCaveWithKey");

        when(request.getParameter("action")).thenReturn("go_to_cave");
        when(session.getAttribute("hasKey")).thenReturn(true);

        servlet.doPost(request, response);

        verify(response).sendRedirect("/Final-Project/cave");

        System.out.println("Passed: testDoPostGoToCaveWithKey");
    }

    @Test
    void testDoGetTalkToOwlWithSword() throws Exception {
        System.out.println("Running test: testDoGetTalkToOwlWithSword");

        when(request.getParameter("action")).thenReturn("talk_to_owl");
        when(session.getAttribute("hasSword")).thenReturn(true);
        when(request.getRequestDispatcher("/WEB-INF/Views/owl.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("message"), eq("The owl sees your sword and perceives it as a threat! The owl attacks and kills you."));

        verify(dispatcher).forward(request, response);

        System.out.println("Passed: testDoGetTalkToOwlWithSword");
    }

    @Test
    void testDoGetTalkToOwlWithoutSword() throws Exception {
        System.out.println("Running test: testDoGetTalkToOwlWithoutSword");

        when(request.getParameter("action")).thenReturn("talk_to_owl");
        when(session.getAttribute("hasSword")).thenReturn(false);
        when(request.getRequestDispatcher("/WEB-INF/Views/owl.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("message"), eq("The owl greets you and shares its wisdom."));

        verify(dispatcher).forward(request, response);

        System.out.println("Passed: testDoGetTalkToOwlWithoutSword");
    }
}

