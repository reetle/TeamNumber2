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
    <div class="col-sm-4 spaceAround">
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
                <button class="button" type="submit">Books</button>
            </form>
        </div>
        <c:if test="${person.role=='admin'}">
            <div>
                <form action="/app/allPersons" method="get">
                    <button class="button-active" type="submit">Persons</button>
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

    <div class="col-sm-8 top padding-top">


        <div>
            <table class="booksTable tableButtonsOnTopNone">
                <tr>
                    <th>Person Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Edit</th>
                    <th>Remove</th>
                    <c:forEach var="person" items="${persons}" varStatus="status">
                </tr>
                <tr>
                    <td>${person.id}</td>
                    <td>${person.firstName}</td>
                    <td>${person.surname}</td>
                    <td>${person.email}</td>
                    <td>${person.role}</td>
                    <td>
                            <form action="/app/person/edit/${person.id}" method="get">
                                <button class="button autoButton" type="submit">Edit</button>
                            </form>

                        </td>
                        <td>
                            <form action="/app/person/delete_ask_confirmation/${person.id}" method="get">
                                <button class="button autoButton" type="submit">Remove</button>
                            </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</body>
</html>