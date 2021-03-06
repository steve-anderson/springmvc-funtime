<%--
  Created by IntelliJ IDEA.
  User: swa
  Date: 4/27/19
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Fun Time</title>
    <link href="<c:url value="/resources/main.css" />" rel="stylesheet" />
</head>
<body>
<h1>Player</h1>
<a href="list">Player List</a>

<c:if test="${found}">
    <div class="card">
        <p><div class="label">Email: </div><c:out value="${player.email}" /></p>
        <p><div class="label">First name: </div><c:out value="${player.firstName}" /></p>
        <p><div class="label">Middle name: </div><c:out value="${player.middleName}" /></p>
        <p><div class="label">Last name: </div><c:out value="${player.lastName}" /></p>
    </div>
    <form:form method="post" action="../game/list" modelAttribute="playerRequest">
        <input id="id" name="id" type="hidden" value="${player.id}" />
        <input name="Games" type="submit" value="Games" />
    </form:form>
</c:if>
<c:if test="${!found}">
    <div class="card">
        <p>Player not found.</p>
    </div>
</c:if>
</body>
</html>
