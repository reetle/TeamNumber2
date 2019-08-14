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
<form action="/app/person/borrowed_books" method="get">
    <button class="button2" type="submit">BORROWED BOOKS</button>
</form>
<br>
<form action="/app/person/lend" method="get">
    <button class="button2" type="submit">LEND BOOKS</button>
</form>
<br>
<form action="/app/person/books" method="get">
    <button class="button2" type="submit">RETURN BOOKS</button>
</form>
</body>
</html>
