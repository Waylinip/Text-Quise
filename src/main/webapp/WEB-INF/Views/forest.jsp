<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forest</title>
</head>
<body>

     <%
            String playerName = (String) session.getAttribute("playerName");
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Adventurer"; // Имя по умолчанию
            }
        %>
        <h1>Welcome to the Forest, <%= playerName %>!</h1>
      <p>You find yourself in a dense, ancient forest. The air is cool and filled with the scent of pine and moss. Sunlight filters through the towering trees, casting dancing shadows on the forest floor. Somewhere in the distance, you hear the sound of rustling leaves and a faint, melodic bird song. The forest feels alive, as if it’s watching you.</p>

        <p>Choose your actions carefully!</p>

    <%
        Boolean explored = (Boolean) request.getAttribute("explored");
        Boolean hasKey = (Boolean) request.getSession().getAttribute("hasKey");
        Boolean hasSword = (Boolean) request.getSession().getAttribute("hasSword");

        if (hasSword == null) {
            hasSword = false;
        }

        if (explored != null && explored) {
    %>
        <h2>You have explored the forest!</h2>
<p>As you delve deeper into the forest, the trees grow taller and denser. The sunlight becomes scarce, and the air feels cooler. You come across a clearing where three distinct paths present themselves:</p>
    <ul>
        <li>A shining **sword** stands embedded in a stone, radiating a mysterious glow.</li>
        <li>A dark **cave** entrance beckons, its interior obscured by shadow.</li>
        <li>A towering tree with an **owl’s nest** sits high above, its branches swaying gently in the breeze.</li>
    </ul>
    <p>What will you choose?</p>
        <% if (!hasSword) { %>
            <form action="http://localhost:8080/Final-Project/forest" method="POST">
                <button type="submit" name="action" value="take_sword">Take the sword</button><br>
            </form>
        <% } else { %>
            <h1>The Ancient Sword</h1>
               <p>You carefully approach a stone pedestal standing in the heart of the forest. Moss and vines wrap around its base, hinting at its great age. Resting atop the pedestal is a sword unlike any you’ve ever seen.</p>
               <p>The blade shimmers faintly, as though holding the light of a thousand stars within its steel. Strange runes are etched along its edge, pulsating softly with a mystical glow. The hilt is adorned with intricate carvings of dragons, their eyes made of tiny gemstones that seem to watch your every move.</p>
               <p>As you reach for the sword, a sudden gust of wind rustles the leaves around you, almost as if the forest itself is warning you to proceed with caution.</p>
               <p>The inscription on the pedestal reads:</p>
               <blockquote>
                   "Only the brave may wield me. Use my power wisely, for I grant strength to conquer, but also the burden of responsibility."
               </blockquote>

        <% } %>

        <form action="http://localhost:8080/Final-Project/forest" method="POST">
            <button type="submit" name="action" value="go_to_cave">Go to the Cave</button><br>
            <button type="submit" name="action" value="talk_to_owl">Go to the Owl's nest</button>
        </form>
    <%
        } else {
    %>
        <form action="http://localhost:8080/Final-Project/forest" method="POST">
            <button type="submit" name="action" value="explore">Explore the forest</button>
        </form>
    <%
        }

        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <p><%= errorMessage %></p>
    <%
        }
    %>
</body>
</html>


