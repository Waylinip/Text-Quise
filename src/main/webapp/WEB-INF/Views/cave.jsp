<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to the Cave</title>
</head>
<body>

    <h1>Welcome to the Cave, <%= session.getAttribute("playerName") %></h1>

    <%

        Boolean hasTorch = (Boolean) session.getAttribute("hasTorch");
        Boolean holdingTorch = (Boolean) session.getAttribute("holdingTorch");
        Boolean passedTrap = (Boolean) session.getAttribute("passedTrap");

        if (passedTrap != null && passedTrap) {
            out.println("<p>You successfully passed the traps! You are now facing the Dragon!</p>");
            out.println("<a href='/Final-Project/dragon'>Proceed to the Dragon</a>");
        } else {
            if (hasTorch == null || !hasTorch) {
    %>

    <p>
        You find yourself standing before the entrance to a large, shadowy cave. The air is cool and damp, and the faint sound of dripping water echoes from within.
        The entrance is shrouded in darkness, and you feel an eerie chill crawl down your spine.
        It's clear this place holds secrets, but it might also hold danger.
    </p>
    <p>
        You know you'll need a light source to proceed safely, but you can also risk venturing further without one. The decision is yours.
    </p>
    <form action="/Final-Project/cave" method="POST">
        <button type="submit" name="action" value="search_torch">Search the Cave</button><br>
        <button type="submit" name="action" value="go_into_cave">Go into the Cave (Risky)</button><br>
    </form>
    <%
            } else if (hasTorch && (holdingTorch == null || !holdingTorch)) {
    %>
   <p>
       As you stand at the cave entrance, the darkness ahead is overwhelming. You know that without light, your journey will be perilous. You hold the torch in your hand, and it's clear that it could help you, but you're unsure how to proceed.
   </p>
   <p>
       You could take the torch and cautiously step into the cave, using its flickering light to guide you. This might provide the safety you need, but it could also reveal things you'd rather not see. Or, you might consider a more daring approach: throwing the torch into the darkness, hoping to trigger a hidden trap or mechanism. The light might not be the only way to navigate, and perhaps the trap will reveal a safe path forward.
   </p>
   <p>
       The choice is yours, adventurer: will you risk the unknown and take the torch, or throw it and trust in the shadows to guide you? Choose wisely, for your decision could make all the difference in this dark and dangerous cave.
   </p>
    <form action="/Final-Project/cave" method="POST">
        <button type="submit" name="action" value="take_torch">Take the Torch</button><br>
        <button type="submit" name="action" value="throw_torch">Throw the Torch (Risky)</button><br>
    </form>
    <%
            } else if (holdingTorch != null && holdingTorch) {
    %>
     <p>You grip the torch tightly, its warmth surprising you. As the flicker of light begins to grow, you realize with a start — this isn't just any torch... it's a magical torch!</p>

         <p>The torch is extinguished. You need to hold it to make it burn.</p>

         <p>As you proceed deeper into the cave, the dim light of your magical torch reveals a wide pit stretching across the path. Sharp spikes glint menacingly at the bottom of the pit, and on the other side, you notice a lever embedded into the wall. It seems like the only way forward is to cross this deadly trap.</p>

         <p>But with the light from the torch, you now see the hidden edges of the pit and the spikes clearly, guiding your every step. You move carefully, using the torch’s glow to avoid the deadly spikes. With confidence, you leap across the gap and reach the lever. You pull it, and the ground shakes as the trap is disabled.</p>

         <p>Now, with the path cleared, you continue deeper into the cave. The light from your torch illuminates the dark, rocky walls as you move forward, ready for whatever might lie ahead. You have successfully passed the trap and are one step closer to the end of the cave.</p>

   <form action="/Final-Project/cave" method="POST">
       <button type="submit" name="action" value="proceed_to_trap">Move on through the cave</button><br>
   </form>


    <%
            }
        }
    %>

</body>
</html>



