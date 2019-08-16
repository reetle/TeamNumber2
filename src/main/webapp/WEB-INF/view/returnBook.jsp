<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-16
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lend books</title>
</head>
<body>
<h3>Raamatu tagastamiseks sisesta raamatu triipkood.</h3>
<form:form method="POST" action="/app/book/return" modelAttribute="book">
    <table>
        <tr>
            <td><form:label path="bookid">Triipkood</form:label></td>
            <td><form:input path="bookid"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Tagasta"/></td>
        </tr>
    </table>
</form:form>

<br>
<form action="/app//person/profile" method="get">
    <button class="button2" type="submit">BACK TO HOME</button>
</form>


</body>
</html>
