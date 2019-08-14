<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 2019-08-14
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<div align="center">
    <h1>Books List</h1>

    <table border="1">
        <th>Name</th>
        <th>Author</th>

        <tr>
            <td>${book.name}</td>
            <td>${book.author}</td>
        </tr>
    </table>
</div>
</body>
</html>
