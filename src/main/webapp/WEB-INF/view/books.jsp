<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AVAILABLE BOOKS</title>
</head>
<body>
<div align="center">
    <h1>Books List</h1>

    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Author</th>
        <th>Status</th>


        <c:forEach var="book" items="${books}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.status}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/app//person/profile" method="get">
        <button class="button2" type="submit">BACK TO HOME</button>
    </form>
</div>
</body>
</html>
