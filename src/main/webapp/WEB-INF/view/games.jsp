<%--
  Created by IntelliJ IDEA.
  User: swa
  Date: 4/27/19
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Fun Time</title>
</head>
<body>
<h1>Games for Player</h1>
<a href="../player/list">Player List</a>

<c:if test="${found}">
<p>Email: <a href="../player/${player.id}"><c:out value="${player.email}" /></a></p>
<p>First name: <c:out value="${player.firstName}" /></p>
<p>Middle name: <c:out value="${player.middleName}" /></p>
<p>Last name: <c:out value="${player.lastName}" /></p>

<table>
    <thead>
        <tr>
            <td>Game</td>
            <td>Won</td>
            <td colspan="4">Opponent</td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td>Email</td>
            <td>Last name</td>
            <td>First name</td>
            <td>Middle name</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="game" items="${games}">
        <tr>
            <td><c:out value="${game.name}" /></td>
            <td><c:out value="${game.won ? 'Y' : 'N'}" /></td>
            <td><a href="../player/${game.opponent.id}"><c:out value="${game.opponent.email}" /></a></td>
            <td><c:out value="${game.opponent.lastName}" /></td>
            <td><c:out value="${game.opponent.firstName}" /></td>
            <td><c:out value="${game.opponent.middleName}" /></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${!found}">
    <p>Player not found.</p>
</c:if>
</body>
</html>
