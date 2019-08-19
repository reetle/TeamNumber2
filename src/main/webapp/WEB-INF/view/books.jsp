<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>



    <!--bootstrap-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- stylesheet-->
    <link rel="stylesheet" type="text/css" href="../../stylesheet.css">
    <!-- google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">


</head>
<body id="bodySolid">

<div class="container">
    <div class="box">


            <table class="booksTable">

                <tr>
                    <th colspan="4">Book Directory</th>
                </tr>

                <th>Name</th>
                <th>Author</th>
                <th>Status</th>
                <c:if test="${person.role=='admin'}">
                    <th>Commands</th>
                </c:if>
                <c:forEach var="book" items="${books}" varStatus="status">
                    <tr>

                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>${book.status}</td>
                        <c:if test="${person.role=='admin'}">
                            <td>
                                <form action="/app/book/${book.bookid}/edit" method="get">
                                    <button class="button" type="submit">Edit</button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <c:if test="${person.role=='admin'}">
                <td>
                    <form action="/app/book/new" method="get">
                        <button class="button" type="submit">Add New</button>
                    </form>
                </td>
            </c:if>
            <form action="/app/person/profile" method="get">
                <button class="button" type="submit">Back to Profile</button>
            </form>

    </div>
</div>
</body>
</html>
