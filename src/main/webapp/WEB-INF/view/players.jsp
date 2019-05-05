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
    <link href="<c:url value="/resources/main.css" />" rel="stylesheet" />
</head>
<body>
<h1>Players</h1>
<table>
    <thead>
        <tr>
            <td>Email</td>
            <td>Last name</td>
            <td>First name</td>
            <td>Middle name</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="player" items="${players}">
        <tr>
            <td><a href="${player.id}"><c:out value="${player.email}" /></a></td>
            <td><c:out value="${player.lastName}" /></td>
            <td><c:out value="${player.firstName}" /></td>
            <td><c:out value="${player.middleName}" /></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
