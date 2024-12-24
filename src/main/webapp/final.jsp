<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quest Conclusion</title>
</head>
<body>
    <h1>Quest Completed!</h1>

    <p><%= request.getAttribute("finalMessage") %></p>

    <form action="<%= request.getContextPath() %>/reset" method="get">
        <button type="submit">Start New Quest</button>
    </form>
</body>
</html>
