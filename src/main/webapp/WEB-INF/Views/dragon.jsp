<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Three-Headed Dragon</title>
</head>
<body>
    <h1>The Three-Headed Dragon</h1>

    <%
        Boolean snakeAwakened = (Boolean) session.getAttribute("snakeAwakened");
        String gameState = (String) session.getAttribute("gameState");
        String message = (String) request.getAttribute("message");
        Boolean hasSword = (Boolean) session.getAttribute("hasSword");
        Boolean showSeaButton = (Boolean) request.getAttribute("showSeaButton");

        if (snakeAwakened != null && snakeAwakened) {
    %>
         <p>The Three-Headed Dragon wakes up!</p>
                <p>You can feel the heat from the dragon’s fiery breath, and the sound of its breathing grows louder. Each of its three heads opens its eyes, staring at you with an intensity that could melt stone. The dragon growls, and you know that it is no longer asleep...</p>
                <p>The dragon then continues, his voice a low, rumbling growl that echoes in the cavern: “You seek to leave this cave, adventurer. But I cannot allow a fool to pass. If you wish to proceed, I will give you a chance, but beware—one mistake and you will join the forgotten in this cursed place, a mere corpse lost to time. Choose wisely, or face the consequences.”</p>
                <p>It speaks in riddles: “I have cities, but no houses. I have forests, but no trees. I have rivers, but no water. What am I?”</p>

        <form action="<%= request.getContextPath() %>/dragon" method="POST">
            <input type="text" name="answer" placeholder="Your answer" required>
            <button type="submit" name="action" value="submit_answer">Submit Answer</button>
        </form>

        <%
            if (message != null) {
        %>
            <p><%= message %></p>
        <%
            }

            if ("success".equals(gameState)) {
        %>
            <a href="<%= request.getContextPath() %>/sea">Go to the sea</a>
        <%
            } else if ("toll".equals(gameState)) {
        %>
            <form action="<%= request.getContextPath() %>/dragon" method="POST">
                <button type="submit" name="action" value="fight">Fight the dragon</button>
            </form>
        <%
            }

            if (showSeaButton != null && showSeaButton) {
        %>
            <a href="<%= request.getContextPath() %>/sea">Proceed to the sea</a>
        <%
            }
        %>
    <%
        } else {
    %>
       <p>You move deeper into the cave, your torch burning brightly, illuminating the dark, damp walls. As you advance, a faint glimmer of light appears in the distance, growing stronger as you move closer. The cave begins to slope upward, and the air feels warmer.</p>

              <p>Finally, you step out into the light — the entrance to the cave is now within your reach. But as you approach, you realize that the exit is blocked by a massive creature: a three-headed dragon! It lays sprawled across the cave floor, its enormous form almost completely filling the vast cavern.</p>

              <p>The dragon’s three heads rest on the ground, and its massive wings are folded tightly against its body. Each of the dragon’s heads is larger than a man, with sharp, gleaming teeth visible even in the dim light. Its scales shimmer faintly, reflecting the torchlight. Despite its immense size, the creature lies motionless, breathing slowly, each deep inhale causing the ground to tremble slightly.</p>

              <p>The cave around the dragon is dark and silent, the only light coming from your torch and the faint glimmer of sunlight at the far end of the cavern, where you can see a narrow passageway leading to the outside world. The smell of smoke and sulfur fills the air, and the heat from the dragon's body is almost unbearable, even from a distance.</p>

              <p>The cave itself is large, with jagged rock formations hanging from the ceiling and scattered across the floor. A large pool of dark water sits in one corner of the cavern, its surface perfectly still. There’s a sense of foreboding in the air, and you realize that this is no ordinary place — it’s the lair of the Three-Headed Dragon.</p>

              <p>As you try to move closer, you hear a low growl emanate from one of the dragon’s heads. It’s still asleep, but there’s no telling how long that will last...</p>

              <form action="<%= request.getContextPath() %>/dragon" method="POST">

        <form action="<%= request.getContextPath() %>/dragon" method="POST">
            <button type="submit" name="action" value="sneak_past">Try to sneak past</button>
            <button type="submit" name="action" value="wake_up">Wake the dragon up</button>
        </form>
    <%
        }
    %>
</body>
</html>





