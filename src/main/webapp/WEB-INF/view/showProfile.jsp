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
<body id="bodyGradient">

<div class="row">
    <div class="col-sm-4" id="left">
        <!--div class="container"-->
        <!--div class="box"-->
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
            <form action="/app/person/books" method="get">
                <button class="button" type="submit">Returning</button>
            </form>
        </div>
        <div>
            <form action="/app/library/books" method="get">
                <button class="button" type="submit">Book Directory</button>
            </form>
        </div>

<%--        <div class="d-sm-none">
            <p>Swipe Down for Your Profile</p>

        </div>--%>


        <!--/div>
    </div-->
    </div>
    <div class="col-sm-8" id="right">
        <%--    <div class="container">--%>
        <%--        <div class="box">--%>
        <%--           <div>--%>


        <table class="booksTable">
            <tr>
                <th colspan="2">Your books</th>

            </tr>
            <%--<th>No</th>--%>
            <th>Name</th>
            <th>Author</th>


            <c:forEach var="book" items="${books}" varStatus="status">
                <tr>
                    <%--<td>${status.index + 1}</td>--%>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                </tr>
            </c:forEach>
        </table>
        <%--       </div>--%>
        <%--        </div>--%>
        <%--    </div>--%>
    </div>
</div>
</body>
</html>