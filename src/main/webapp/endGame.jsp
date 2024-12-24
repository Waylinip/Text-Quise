<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>End Game</title>
</head>
<body>
    <h1>Game Over!</h1>
    <%
        String message = (String) session.getAttribute("message");
        if (message != null) {
    %>
        <p><%= message %></p>
    <%
            session.removeAttribute("message");
        }
    %>
    <p>Thank you for playing!</p>

    <form action="<%= request.getContextPath() %>/reset" method="get">
        <button type="submit">Play Again</button>
    </form>
</body>
</html>