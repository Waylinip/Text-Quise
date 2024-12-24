package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/magicstone")
public class MagicStoneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String finalMessage = "";

        if ("share_stone".equals(action)) {

            finalMessage = " Your Kindness United the Worlds\n" +
                    "        You chose to share the magical stone with the mermaid, trusting in the power of unity. As the stone left your hands, it glowed brightly, spreading warmth and light across the sea and sky.\n" +
                    "        Mystical creatures and humans began to live in harmony, their differences bridged by your selfless act. The mermaid thanked you, her smile filled with hope, as the sea transformed into a haven of peace and beauty.\n" +
                    "        You returned home, knowing your decision had changed the world for the better. Your name became a symbol of unity and compassion, remembered by both land and sea for generations to come.";
        } else if ("keep_stone".equals(action)) {

            finalMessage = "You chose to keep the magical stone, tempted by its immense power. As you held it, a surge of energy coursed through you, granting abilities beyond imagination.\n" +
                    "        However, the power came at a cost. The sea grew darker, its creatures restless and hostile. The mermaid's gaze turned from hope to despair as she vanished into the depths, leaving you alone with your decision.\n" +
                    "        The once vibrant ocean became a shadow of its former self, and the balance of nature was forever disrupted. While you became known as a figure of great power, your name was whispered with fear and regret across the land and sea.\n" +
                    "        The price of your choice weighed heavily on your soul, a reminder that some treasures are not meant to be kept.";
        } else if ("die".equals(action)) {

            request.getSession().setAttribute("message", "You tried to wield the magical stone without understanding its true power. As its energy surged uncontrollably, you felt a sharp pain coursing through your body. The stone, rejecting your reckless intentions, unleashed a devastating burst of energy.\n" +
                    "The world around you darkened as the immense force consumed you. In your final moments, you realized the stone's magic was not something to be taken lightly.\n" +
                    "Your journey ends here, but perhaps, with a wiser heart, you will find a better path next time. Starting over...");
            response.sendRedirect(request.getContextPath() + "/endGame.jsp");
            return;
        }

        request.setAttribute("finalMessage", finalMessage);

        request.getRequestDispatcher("/final.jsp").forward(request, response);
    }
}
