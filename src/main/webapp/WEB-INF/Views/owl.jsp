<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Owl's Riddle</title>
</head>
<body>
    <h1>Owl's Riddle</h1>
<p>You step cautiously into a clearing illuminated by faint moonlight. Perched atop an ancient, gnarled tree is an enormous owl, its golden eyes glowing like lanterns in the dark. Its wings are spread wide, and its feathers shimmer faintly, as though dusted with stardust.</p>
    <p>The owl regards you silently for a moment before speaking in a deep, resonant voice that seems to echo through the forest:</p>
    <blockquote>
        "You have entered my domain, traveler. If you wish to proceed, you must answer my riddle."
    </blockquote>
    <p>The owl asks: "<%= session.getAttribute("currentRiddle") %>"</p>
    <form action="/Final-Project/owl" method="POST">
        <input type="text" name="answer" placeholder="Your answer" required>
        <button type="submit">Submit Answer</button>
    </form>

    <%
        Integer attempts = (Integer) session.getAttribute("attempts");
        if (attempts == null) {
            attempts = 0;
        }
    %>
    <p>You have <%= 2 - attempts %> attempt(s) left.</p>
</body>
</html>