<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <div>
            <table class="booksTable">
                <tr>
                    <th colspan="2">You have borrowed:</th>
                </tr>
                <th>Name</th>
                <th>Author</th>

                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                </tr>
            </table>
        </div>

        <form action="/app/person/profile" method="get">
            <button class="button" type="submit">Back to Profile</button>
        </form>

    </div>
</div>
</body>
</html>
