
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Start Game</title>
</head>
<body>
    <h1>Welcome to the Adventure!</h1>
    <p>Your adventure begins here. In this quest, you'll face challenges, discover hidden secrets, and make decisions that shape your journey.</p>
    <p>Before you start, tell us your name so we can address you properly during the adventure!</p>

    <form action="/Final-Project/" method="POST">
        <label for="playerName">Enter your name: </label>
        <input type="text" id="playerName" name="playerName" required>
        <button type="submit">Start Adventure</button>
    </form>
</body>
</html>