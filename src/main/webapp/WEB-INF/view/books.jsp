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

<body id="bodySolid">

<div class="row">
    <div class="col-sm-4" id="left">
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
        <div>
            <form action="/" method="get">
                <button class="button" type="submit">Log Out</button>
            </form>
        </div>
        </div-->
    </div>

    <div class="col-sm-8" id="right">
        <div>
            <table>
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



        <table class="booksTable">

            <th>Name</th>
            <th>Author</th>
            <th>Status</th>
            <c:if test="${person.role=='admin'}">
               <th>Edit</th>
            </c:if>
            <c:forEach var="book" items="${books}" varStatus="status">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.status}</td>
                    <c:if test="${person.role=='admin'}">
                        <td>
                            <form action="/app/book/edit/${book.bookid}" method="get">
                                <button class="button" type="submit">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="/app/book/remove/${book.bookid}" method="get">
                                <button class="button" type="submit">Remove</button>
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







    </div>
</body>
</html>