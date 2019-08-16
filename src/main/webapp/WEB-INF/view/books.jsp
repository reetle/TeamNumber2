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

<div class="container">
    <div class="box">


            <table class="booksTable">

                <tr>
                    <th colspan="3">Book Directory</th>
                </tr>

                <th>Name</th>
                <th>Author</th>
                <th>Status</th>


                <c:forEach var="book" items="${books}" varStatus="status">
                    <tr>

                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>${book.status}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <form action="/app/person/profile" method="get">
                <button class="button" type="submit">BACK TO HOME</button>
            </form>

    </div>
</div>
</body>
</html>
