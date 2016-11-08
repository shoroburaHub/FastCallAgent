<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: koko
  Date: 29.08.16
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script src="/resources/js/start.js"></script>
</head>
<body>

<%--${time}--%>
<br>

<c:forEach varStatus="vs" var="client" items="${clients}">
    <c:if test="${vs.isFirst()}" >

        ${client.dateTime.substring(6)}
        <br>
        <br>
    </c:if>
    <c:if test="${client.isLast}">
        <br>
            ${client.dateTime.substring(6)}
        <br>
        <br>
    </c:if>
    ${client.status} ${client.phone} ${client.dateTime} ${client.callCount}
    <a href="deleteRecord/${client.id}" >delete</a>
    <br>
</c:forEach>

</body>
</html>
