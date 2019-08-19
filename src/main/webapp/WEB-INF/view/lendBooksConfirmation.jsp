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
                <button class="button-active" type="submit">Lending</button>
            </form>
        </div>
        <div>
            <form action="/app/person/return" method="get">
                <button class="button" type="submit">Returning</button>
            </form>
        </div>
        <div>
            <form action="/app/person/profile" method="get">
                <button class="button" type="submit">Books</button>
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
        <table class=>
            <tr>
                <td>Do You want to borrow</td>
            </tr>
            <tr>
                <th>${book.name}</th>
            </tr>
            <tr>
                <td>${book.author}</td>
            </tr>
        </table>
    </div>



        <div>
            <table>
                <tr>
                    <td>
                        <form action="/app/library/book_confirm_yes" method="get">
                            <button class="button" type="submit">Yes</button>
                        </form>
                    </td>
                    <td>
                        <form action="/app/library/book_confirm_no" method="get">
                            <button class="button" type="submit">No</button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>

    </div>
</body>
</html>