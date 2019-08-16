<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-11
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Raamatukogu</title>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>${person.firstName}</td>
    </tr>
    <tr>
        <td>Lastname</td>
        <td>${person.surname}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${person.email}</td>
    </tr>

</table>
<br/>

<form action="/app/library/books" method="get">
    <button class="button2" type="submit">AVAILABLE BOOKS</button>
</form>
<br>
<form action="/app/person/lend" method="get">
    <button class="button2" type="submit">LEND BOOKS</button>
</form>
<br>
<form action="/app//person/return" method="get">
    <button class="button2" type="submit">RETURN BOOKS</button>
</form>

<div align="center">
    <h1>Books List</h1>

    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Author</th>


        <c:forEach var="book" items="${books}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
