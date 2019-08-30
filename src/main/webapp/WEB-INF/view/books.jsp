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
    <link href="https://fonts.googleapis.com/css?family=Exo:800|Montserrat:300&display=swap" rel="stylesheet">


</head>

<body>
<img src="https://seeklogo.com/images/T/tieto-logo-5D4F5D3319-seeklogo.com.png">

<div class="row">
    <div class="col-md-4 spaceAround">
        <div>
            <table>
                <tr>
                    <td align="center">${person.firstName} ${person.surname}</td>
                </tr>
                <tr>
                    <td align="center">${person.email}</td>
                </tr>
            </table>
        </div>

        <div>
            <form action="/app/person/lend" method="get">
                <button class="button" type="submit">Lending</button>
            </form>
        </div>
        <div>
            <form action="/app/book/return" method="get">
                <button class="button" type="submit">Returning</button>
            </form>
        </div>
        <div>
            <form action="/app/library/books" method="get">
                <button class="button-active" type="submit">Books</button>
            </form>
        </div>
        <c:if test="${person.role=='admin'}">
            <div>
                <form action="/app/allPersons" method="get">
                    <button class="button" type="submit">Persons</button>
                </form>
            </div>
        </c:if>
        <div>
            <form action="/app/history" method="get">
                <button class="button" type="submit">History</button>
            </form>
        </div>
        <div>
            <form action="/" method="get">
                <button class="button" type="submit">Log Out</button>
            </form>
        </div>
    </div>

    <div class="col-md-8 top padding-top">
        <div>
            <table class="padding">
                <tr>
                    <td>
                        <form action="/app/profile" method="get">
                            <button class="button" type="submit">Your Books</button>
                        </form>
                    </td>
                    <td>
                        <form action="/app/library/books" method="get">
                            <button class="button-active" type="submit">All Books</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>

        <div>
            <c:if test="${person.role=='admin'}">
                <table class="padding">
                    <td>
                    <form action="/app/book/new" method="get">
                        <button class="button" type="submit">Add New</button>
                    </form>
                    </td>
                </table>
            </c:if>
        </div>

        <div>
            <table class="booksTable">
                <tr>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Status</th>
                        <c:if test="${person.role=='admin'}">
                    <th>Edit</th>
                    <th>Remove</th>
                    </c:if>
                    <c:forEach var="book" items="${books}" varStatus="status">
                </tr>
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.genre}</td>
                    <td>${book.status}</td>
                    <c:if test="${person.role=='admin'}">
                    <td>
                            <form action="/app/book/edit/${book.bookid}" method="get">
                                <button class="button" type="submit" id="autoButton">Edit</button>
                            </form>

                        </td>
                        <td>
                            <form action="/app/book/delete/${book.bookid}" method="get">
                                <button class="button" type="submit" id="autoButton">Remove</button>
                            </form>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>