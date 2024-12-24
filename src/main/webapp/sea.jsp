<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sea Adventure</title>
</head>
<body>
    <h1>You are at the Sea,<%= session.getAttribute("playerName") %>!</h1>
    <p>You have just emerged from the dark cave, your heart still racing from the battle with the dragon. As you step out into the open air, you are greeted by the sight of something truly magnificent. Before you lies a vast, endless sea, its waters sparkling in the sunlight. The waves crash against jagged rocks, sending sprays of mist into the air. The salty scent of the ocean fills your lungs, and the sound of the waves is both calming and powerful.</p>

       <p>The sea stretches far beyond the horizon, its surface shimmering like a vast mirror. A cool breeze brushes against your face, and the warmth of the sun soothes your tired body. As you stand there, lost in the beauty of the scene, you hear a soft, melodious voice carried on the wind. You turn and look closely, trying to find its source.</p>

       <p>There, perched upon a large rock in the middle of the sea, sits a stunning mermaid. Her long, flowing hair shimmers like silver under the sunlight, and her tail glistens with the colors of the ocean. She smiles warmly at you, as if she has been waiting for your arrival. She speaks:</p>

       <p>"I see you have defeated the dragon, brave adventurer. Your strength and courage have not gone unnoticed. I have been guarding an ancient chest for centuries, but it cannot be opened by just anyone. I need your help to unlock its secrets."</p>

       <p>"This chest has always been here, resting quietly by the sea, waiting for the one who could solve its riddle. It holds an immense power, and only the worthy can open it."</p>

       <p>"In order to open the chest, you must answer my riddle. Are you ready?"</p>

       <p>As you step closer to the chest, you notice that an inscription is carved into its surface, worn and weathered by time. The words read:</p>



    <%
        Boolean riddleSolved = (Boolean) session.getAttribute("riddleSolved");
        Boolean chestOpened = (Boolean) session.getAttribute("chestOpened");

        if (riddleSolved == null) riddleSolved = false;  // Если загадка не решена, по умолчанию - false
        if (chestOpened == null) chestOpened = false;    // Если сундук не открыт, по умолчанию - false
    %>

    <% if (!riddleSolved) { %>

        <p>"I bring light to darkness, but I cannot be seen. I can be everywhere, but I cannot be caught. What am I?"</p>
        <form method="post" action="http://localhost:8080/Final-Project/sea">
            <button type="submit" name="action" value="star">Star</button>
            <button type="submit" name="action" value="light">Light</button>
            <button type="submit" name="action" value="shadow">Shadow</button>
        </form>
    <% } else if (riddleSolved && !chestOpened) { %>
       <p>The riddle has been solved! You can now open the chest.</p>
        <form method="post" action="http://localhost:8080/Final-Project/sea">
            <button type="submit" name="action" value="open_chest">Open the chest</button>
        </form>
    <% } else if (chestOpened) { %>
        <p>The chest is open, revealing a magical stone that can grant any wish!</p>

        <form method="post" action="http://localhost:8080/Final-Project/magicstone">
            <button type="submit" name="action" value="share_stone">Share the stone with others</button>
            <button type="submit" name="action" value="keep_stone">Keep the stone for yourself</button>
            <button type="submit" name="action" value="die">Try to use the stone recklessly</button>
        </form>
    <% } %>

</body>
</html>
