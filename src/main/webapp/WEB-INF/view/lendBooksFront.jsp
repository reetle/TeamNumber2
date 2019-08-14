<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-14
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lend books</title>
</head>
<body>
<h3>Raamatu laenutamiseks sisesta raamatu triipkood.</h3>
<form:form method="POST" action="/app/library/book_confirmation" modelAttribute="book">
    <table>
        <tr>
            <td><form:label path="bookid">Triipkood</form:label></td>
            <td><form:input path="bookid"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Laenuta"/></td>
        </tr>
    </table>
</form:form>


</body>
</html>
