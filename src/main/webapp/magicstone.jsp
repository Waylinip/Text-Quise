<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Magic Stone</title>
</head>
<body>
    <h1>The Magical Stone</h1>
    <p>You cautiously open the chest, its ancient wood creaking as it reveals a glowing, ethereal stone. The stone radiates a soft, otherworldly light, casting flickering shadows across the rocks around you. Its surface is smooth, with strange symbols etched into it, pulsing with an energy that you can almost feel in the air. The mermaid watches you closely, her eyes gleaming with anticipation as if she knows the stone holds great power.</p>
    <p>The air feels charged with magic, and for a moment, you sense that anything is possible with the power this stone holds.</p>

    <%
        String action = request.getParameter("action");
        if ("share_stone".equals(action)) {
    %>

    <%
        } else if ("keep_stone".equals(action)) {
    %>

        } else if ("die".equals(action)) {
    %>

    <%
        }
    %>

    <form method="get" action="http://localhost:8080/Final-Project/sea">
        <button type="submit">Back to the Sea</button>
    </form>
</body>
</html>

